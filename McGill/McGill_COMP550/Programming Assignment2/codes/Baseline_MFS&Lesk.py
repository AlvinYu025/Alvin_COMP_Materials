from nltk.corpus import wordnet as wn
from nltk.wsd import lesk
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from data_loader import WSDLoader
import spacy

nltk.download("wordnet")
nltk.download("stopwords")
nltk.download("punkt")
nltk.download("omw-1.4")


class WSD:
    def __init__(self, data_file, key_file):
        self.loader = WSDLoader(data_file, key_file)
        self.loader.load_instances()
        self.loader.load_key()

        # Initialize NLTK resources
        self.lemmatizer = WordNetLemmatizer()
        self.stop_words = set(stopwords.words('english'))

        # Initialize NER model for preprocessing
        self.NERmodel = spacy.load("en_core_web_sm")

    def preprocess_text(self, text):
        # Tokenize text using SpaCy
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

    def most_frequent_sense(self, lemma, pos):
        synsets = wn.synsets(lemma, pos=pos)
        return synsets[0] if synsets else None

    def lesk_wsd(self, context, lemma, pos_tag):
        # Convert POS tags to WordNet POS tags
        wn_pos = {'NN': 'n', 'NNS': 'n', 'NNP': 'n', 'NNPS': 'n',  # Noun forms
                  'VB': 'v', 'VBD': 'v', 'VBG': 'v', 'VBN': 'v', 'VBP': 'v', 'VBZ': 'v',  # Verb forms
                  'JJ': 'a', 'JJR': 'a', 'JJS': 'a',  # Adjective forms
                  'RB': 'r', 'RBR': 'r', 'RBS': 'r'}  # Adverb forms

        nltk_pos = wn_pos.get(pos_tag, None)
        return lesk(context, lemma, pos=nltk_pos)

    def apply_wsd_methods(self, instances, keys):
        results = []

        for instance_id, instance in instances.items():
            context = " ".join(instance.context)
            lemma = instance.lemma
            pos_tag = instance.pos

            # Convert to WordNet POS for most_frequent_sense
            wn_pos = {'NN': wn.NOUN, 'NNS': wn.NOUN, 'NNP': wn.NOUN, 'NNPS': wn.NOUN,
                      'VB': wn.VERB, 'VBD': wn.VERB, 'VBG': wn.VERB, 'VBN': wn.VERB, 'VBP': wn.VERB, 'VBZ': wn.VERB,
                      'JJ': wn.ADJ, 'JJR': wn.ADJ, 'JJS': wn.ADJ,
                      'RB': wn.ADV, 'RBR': wn.ADV, 'RBS': wn.ADV}.get(pos_tag, None)

            # Most Frequent Sense
            mfs = self.most_frequent_sense(lemma, wn_pos)
            mfs_result = mfs.name() if mfs else None

            # Lesk Algorithm Sense
            lesk_synset = self.lesk_wsd(self.preprocess_text(context), lemma, pos_tag)
            lesk_result = lesk_synset.name() if lesk_synset else None

            correct_answers = keys.get(instance_id, [])

            results.append((instance_id, correct_answers, mfs_result, lesk_result))

        return results

    def calculate_accuracy(self, results):
        mfs_correct = 0
        lesk_correct = 0
        total = len(results)

        for instance_id, correct_answers, mfs_result, lesk_result in results:
            if mfs_result in correct_answers:
                mfs_correct += 1
            if lesk_result in correct_answers:
                lesk_correct += 1

        mfs_accuracy = mfs_correct / total if total > 0 else 0
        lesk_accuracy = lesk_correct / total if total > 0 else 0
        return mfs_accuracy, lesk_accuracy

    def run(self):
        # Evaluate on the test set
        test_instances = {k: v for k, v in self.loader.get_test_instances().items() if k in self.loader.get_test_key()}
        test_key = self.loader.get_test_key()

        test_results = self.apply_wsd_methods(test_instances, test_key)
        mfs_accuracy, lesk_accuracy = self.calculate_accuracy(test_results)

        print(f"Most Frequent Sense (MFS) Accuracy on Test Set: {mfs_accuracy:.2%}")
        print(f"Lesk Algorithm Accuracy on Test Set: {lesk_accuracy:.2%}")


# Main execution
if __name__ == "__main__":
    data_file = 'multilingual-all-words.en.xml'
    key_file = 'wordnet.en.key'

    wsd = WSD(data_file, key_file)
    wsd.run()
