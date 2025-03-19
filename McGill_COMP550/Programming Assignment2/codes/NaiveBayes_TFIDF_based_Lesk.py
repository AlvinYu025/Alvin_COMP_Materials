from nltk.corpus import wordnet as wn
from nltk.corpus import stopwords
from data_loader import WSDLoader
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import LabelEncoder
import random
import spacy

nltk.download("wordnet")
nltk.download("stopwords")
nltk.download("punkt")
nltk.download("omw-1.4")

class WSD_NB_TFIDF:
    def __init__(self, data_file, key_file):
        # Load data
        self.loader = WSDLoader(data_file, key_file)
        self.loader.load_instances()
        self.loader.load_key()
        self.dev_instances = self.loader.get_dev_instances()
        self.test_instances = self.loader.get_test_instances()
        self.dev_key = self.loader.get_dev_key()
        self.test_key = self.loader.get_test_key()

        self.stop_words = set(stopwords.words('english'))
        self.label_encoder = LabelEncoder()
        self.NERmodel = spacy.load("en_core_web_sm")
        self.model = None

    def preprocess_text(self, text):
        # Tokenize text using SpaCy
        doc = self.NERmodel(text)
        processed_tokens = []

        for token in doc:
            # Check if the token is a proper noun or abbreviation, leave unchanged
            if token.ent_type_ or token.is_upper:
                processed_tokens.append(token.text)
            else:
                processed_tokens.append(token.lemma_.lower())

        processed_text = " ".join(processed_tokens)
        return processed_text

    def prepare_training_data(self):
        # Prepare data by combining context and sense definitions
        training_texts = []
        training_labels = []

        for instance_id, instance in self.dev_instances.items():
            context_sentence = " ".join(instance.context)
            context = self.preprocess_text(context_sentence)

            # Collect data for each correct sense
            correct_senses = self.dev_key.get(instance_id, [])
            for sense_id in correct_senses:
                sense = wn.synset(sense_id)
                definition = self.preprocess_text(sense.definition())
                combined_text = context + " " + definition
                training_texts.append(combined_text)
                training_labels.append(sense_id)

        return training_texts, training_labels

    def feature_extraction(self):
        # Prepare data
        dev_texts, dev_labels = self.prepare_training_data()

        # Encode labels as numeric values
        training_labels_encoded = self.label_encoder.fit_transform(dev_labels)

        # Create and fit a NB model with TF-IDF
        self.model = make_pipeline(TfidfVectorizer(), MultinomialNB(alpha=0.1))
        self.model.fit(dev_texts, training_labels_encoded)
        print("TF-IDF feature calculation and probability estimation complete.")

    def predict_sense(self, context_sentence, lemma):
        # Get candidate senses
        candidate_senses = wn.synsets(lemma)
        if not candidate_senses:
            return None

        # Preprocess context
        context = self.preprocess_text(context_sentence)
        sense_probabilities = {}

        for sense in candidate_senses:
            definition = self.preprocess_text(sense.definition())
            combined_text = context + " " + definition

            try:
                predicted_proba = self.model.predict_proba([combined_text])[0]
                sense_index = self.label_encoder.transform([sense.name()])[0]
                sense_probabilities[sense] = predicted_proba[sense_index]
            except ValueError:
                sense_probabilities[sense] = 0.0

        best_sense = max(sense_probabilities, key=sense_probabilities.get)
        return best_sense

    def evaluate(self):
        correct = 0
        total = len(self.test_instances)

        for instance_id, instance in self.test_instances.items():
            context_sentence = " ".join(instance.context)
            lemma = instance.lemma
            correct_senses = self.test_key.get(instance_id, [])

            predicted_sense = self.predict_sense(context_sentence, lemma)
            if predicted_sense and predicted_sense.name() in correct_senses:
                correct += 1

        accuracy = correct / total if total > 0 else 0
        print(f"TF-IDF Probabilistic Model Accuracy on Test Set: {accuracy:.2%}")

        # Show sample predictions for analysis
        self.show_sample_predictions()

    def show_sample_predictions(self, num_samples=5, seed=9):
        # Control randomness by setting a seed
        random.seed(seed)
        sample_instances = random.sample(list(self.test_instances.items()), num_samples)
        sample_count = 1

        for instance_id, instance in sample_instances:
            context_sentence = " ".join(instance.context)
            lemma = instance.lemma
            correct_senses = self.test_key.get(instance_id, [])

            # Predict the sense using the model
            predicted_sense = self.predict_sense(context_sentence, lemma)
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

    def run(self):
        self.feature_extraction()  # Calculate TF-IDF and probabilities
        self.evaluate()  # Evaluate the model on the test set


# Main execution
if __name__ == "__main__":
    data_file = 'multilingual-all-words.en.xml'
    key_file = 'wordnet.en.key'

    wsd = WSD_NB_TFIDF(data_file, key_file)
    wsd.run()
