import nltk
from nltk.corpus import wordnet as wn
from nltk.corpus import stopwords
from data_loader import WSDLoader
from nltk.stem import WordNetLemmatizer
from transformers import AutoModel, AutoTokenizer, AdamW
import torch
from torch.utils.data import DataLoader, Dataset
import torch.nn.functional as F
from tqdm import tqdm
import random
import spacy

nltk.download("wordnet")
nltk.download("stopwords")
nltk.download("punkt")
nltk.download("omw-1.4")


class WSDPairDataset(Dataset):
    def __init__(self, instances, keys, tokenizer):
        self.pairs = []
        self.labels = []
        self.tokenizer = tokenizer
        self.lemmatizer = WordNetLemmatizer()
        self.stop_words = set(stopwords.words('english'))

        for instance_id, instance in instances.items():
            context_sentence = " ".join(instance.context)
            lemma = instance.lemma
            correct_senses = keys.get(instance_id, [])

            # Create positive examples (context + correct sense definitions)
            for sense_id in correct_senses:
                sense = wn.synset(sense_id)
                definition_sentence = sense.definition()
                self.pairs.append((context_sentence, definition_sentence))
                self.labels.append(1)  # Positive pair

            # Create negative examples (context + incorrect sense definitions)
            incorrect_senses = [synset for synset in wn.synsets(lemma) if synset.name() not in correct_senses]
            for sense in incorrect_senses[:len(correct_senses)]:  # equal no. of positives and negatives
                definition_sentence = sense.definition()
                self.pairs.append((context_sentence, definition_sentence))
                self.labels.append(0)  # Negative pair

    def __len__(self):
        return len(self.pairs)

    def __getitem__(self, idx):
        context, definition = self.pairs[idx]
        inputs = self.tokenizer(context, definition, return_tensors="pt", padding="max_length", max_length=128,
                                truncation=True)
        label = torch.tensor(self.labels[idx], dtype=torch.float)
        return inputs, label


class BERT_WSD:
    def __init__(self, data_file, key_file, model_name="bert-base-uncased", batch_size=8, epochs=10, learning_rate=2e-5,
                 model_save_path="fine_tuned_bert_wsd_epoch10.pth"):
        self.loader = WSDLoader(data_file, key_file)
        self.loader.load_instances()
        self.loader.load_key()
        self.dev_instances = self.loader.get_dev_instances()
        self.test_instances = self.loader.get_test_instances()
        self.dev_key = self.loader.get_dev_key()
        self.test_key = self.loader.get_test_key()

        # Initialize BERT model and tokenizer
        self.tokenizer = AutoTokenizer.from_pretrained(model_name)
        self.model = AutoModel.from_pretrained(model_name)
        self.NERmodel = spacy.load("en_core_web_sm")
        self.batch_size = batch_size
        self.epochs = epochs
        self.learning_rate = learning_rate
        self.model_save_path = model_save_path

    def fine_tune(self):
        # Prepare dataset and dataloader
        train_dataset = WSDPairDataset(self.dev_instances, self.dev_key, self.tokenizer)
        train_dataloader = DataLoader(train_dataset, batch_size=self.batch_size, shuffle=True)

        # Set up optimizer
        optimizer = AdamW(self.model.parameters(), lr=self.learning_rate)

        # Fine-tuning
        self.model.train()
        for epoch in range(self.epochs):
            total_loss = 0
            for batch in tqdm(train_dataloader, desc=f"Epoch {epoch + 1}"):
                inputs, labels = batch
                for key in inputs:
                    # Ensuring inputs are in the shape (batch_size, seq_length)
                    inputs[key] = inputs[key].squeeze(1).to(self.model.device) if inputs[key].dim() == 3 else inputs[
                        key].to(self.model.device)
                labels = labels.to(self.model.device)

                # Forward pass
                outputs = self.model(**inputs)
                context_embedding = outputs.last_hidden_state[:, 0]  # [CLS] token embedding for context
                definition_embedding = outputs.last_hidden_state[:, -1]  # Last token embedding for definition

                # Compute cosine similarity
                similarity_scores = F.cosine_similarity(context_embedding, definition_embedding)
                loss = F.binary_cross_entropy_with_logits(similarity_scores, labels)

                # Backward pass and optimization
                optimizer.zero_grad()
                loss.backward()
                optimizer.step()

                total_loss += loss.item()

            print(f"Epoch {epoch + 1} Loss: {total_loss / len(train_dataloader):.4f}")

        # Save fine-tuned model
        torch.save(self.model.state_dict(), self.model_save_path)
        print(f"Model saved to {self.model_save_path}")

    def load_fine_tuned_model(self):
        # Load the fine-tuned model parameters
        self.model.load_state_dict(torch.load(self.model_save_path))
        self.model.eval()
        print("Fine-tuned model loaded.")


    def preprocess_text(self, text):
        # Tokenize text using SpaCy NER model
        doc = self.NERmodel(text)
        processed_tokens = []

        for token in doc:
            # Check if the token is a proper noun or abbreviation, leave unchanged
            if token.ent_type_ or token.is_upper:
                processed_tokens.append(token.text)
            else:
                # Otherwise, lowercase and lemmatize
                processed_tokens.append(token.lemma_.lower())

        # Join tokens back into a single string
        processed_text = " ".join(processed_tokens)
        return processed_text

    def get_similarity_score(self, context, definition):
        # Preprocess context
        context = self.preprocess_text(context)

        # Tokenize and get BERT embeddings for context and definition
        inputs = self.tokenizer(context, definition, return_tensors="pt", padding="max_length", max_length=128,
                                truncation=True)
        inputs = {k: v.to(self.model.device) for k, v in inputs.items()}

        with torch.no_grad():
            outputs = self.model(**inputs)

        context_embedding = outputs.last_hidden_state[:, 0]  # [CLS] token embedding
        definition_embedding = outputs.last_hidden_state[:, -1]  # Last token embedding

        # Return cosine similarity
        similarity_score = F.cosine_similarity(context_embedding, definition_embedding).item()
        return similarity_score

    def bert_enhanced_lesk(self, context, lemma):
        # Get the best sense based on similarity scores with fine-tuned BERT
        best_sense = None
        max_similarity = -1

        for synset in wn.synsets(lemma):
            definition = synset.definition()
            similarity = self.get_similarity_score(context, definition)

            if similarity > max_similarity:
                max_similarity = similarity
                best_sense = synset

        return best_sense

    def evaluate(self):
        # Evaluation using BERT-enhanced Lesk on the test set
        correct = 0
        total = len(self.test_instances)

        for instance_id, instance in self.test_instances.items():
            context = " ".join(instance.context)
            lemma = instance.lemma
            correct_senses = self.test_key.get(instance_id, [])

            predicted_sense = self.bert_enhanced_lesk(context, lemma)
            if predicted_sense and predicted_sense.name() in correct_senses:
                correct += 1

        accuracy = correct / total if total > 0 else 0
        print(f"BERT-enhanced Lesk WSD Accuracy on Test Set: {accuracy:.2%}")

    def show_sample_predictions(self, num_samples=5, seed=100):
        # Display sample predictions from test set
        random.seed(seed)
        sample_instances = random.sample(list(self.test_instances.items()), num_samples)
        sample_count = 1

        for instance_id, instance in sample_instances:
            context_sentence = " ".join(instance.context)
            lemma = instance.lemma
            correct_senses = self.test_key.get(instance_id, [])

            # Predict the sense using the model
            predicted_sense = self.bert_enhanced_lesk(context_sentence, lemma)
            predicted_sense_name = predicted_sense.name() if predicted_sense else "None"

            # Check if prediction is correct
            is_correct = "Correct" if predicted_sense_name in correct_senses else "Incorrect"

            # Display the output
            print(f"\nSample Prediction {sample_count}:")
            print("Context Sentence:", context_sentence)
            print("Target Word:", lemma)
            print("Candidate Senses:", [sense.name() for sense in wn.synsets(lemma)])
            print("Predicted Sense:", predicted_sense_name)
            print("Actual Sense(s):", correct_senses)
            print("Prediction Status:", is_correct)
            sample_count += 1

    def run(self, load_model=False):
        if load_model:
            self.load_fine_tuned_model()
        else:
            self.fine_tune()  # Fine-tune on the development set

        self.evaluate()  # Evaluate on the test set
        self.show_sample_predictions(num_samples=5, seed=100)


if __name__ == "__main__":
    data_file = 'multilingual-all-words.en.xml'
    key_file = 'wordnet.en.key'

    wsd = BERT_WSD(data_file, key_file)

    # If you want to obtain the data accuracy stated in the report, please download the fine-tuned model
    # @ "https://drive.google.com/file/d/1k-_WAh_oNjOokm6ifsbRlQ9e8BT8UHe1/view?usp=sharing"

    wsd.run(load_model=False)  # Set load_model=True to load the fine-tuned model directly
