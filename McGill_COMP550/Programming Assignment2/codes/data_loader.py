import xml.etree.ElementTree as ET
import codecs
from nltk.corpus import wordnet as wn


class WSDInstance:
    def __init__(self, my_id, lemma, pos, context, index):
        self.id = my_id  # id of the WSD instance
        self.lemma = lemma  # lemma of the word whose sense is to be resolved
        self.pos = pos  # Part of Speech of the lemma
        self.context = context  # lemma of all the words in the sentential context
        self.index = index  # index of lemma within the context

    def __str__(self):
        '''
        For printing purposes.
        '''
        return '%s\t%s\t%s\t%s\t%d' % (self.id, self.lemma, self.pos, ' '.join(self.context), self.index)


class WSDLoader:
    def __init__(self, data_file, key_file):
        self.data_file = data_file
        self.key_file = key_file
        self.dev_instances = {}
        self.test_instances = {}
        self.dev_key = {}
        self.test_key = {}

    def load_instances(self):
        '''
        Load two lists of cases to perform WSD on. The structure that is returned is a dict, where
        the keys are the ids, and the values are instances of WSDInstance.
        '''
        tree = ET.parse(self.data_file)
        root = tree.getroot()

        dev_instances = {}
        test_instances = {}

        for text in root:
            if text.attrib['id'].startswith('d001'):
                instances = test_instances
            else:
                instances = dev_instances
            for sentence in text:
                context = [self.to_ascii(el.attrib['lemma']) for el in sentence]
                for i, el in enumerate(sentence):
                    if el.tag == 'instance':
                        my_id = el.attrib['id']
                        lemma = self.to_ascii(el.attrib['lemma'])
                        pos = el.attrib['pos']
                        instances[my_id] = WSDInstance(my_id, lemma, pos, context, i)
        self.dev_instances = dev_instances
        self.test_instances = test_instances

    def load_key(self):
        '''
        Load the solutions as dicts.
        Key is the id
        Value is the list of correct sense keys in WordNet synset name format.
        '''
        dev_key = {}
        test_key = {}

        with open(self.key_file) as f:
            for line in f:
                if len(line) <= 1:
                    continue
                doc, my_id, sense_keys = line.strip().split(' ', 2)
                sense_keys = sense_keys.split()
                synsets = [self.convert_sense_key_to_synset(sense_key) for sense_key in sense_keys]

                synsets = [s for s in synsets if s is not None]

                if doc == 'd001':
                    test_key[my_id] = synsets
                else:
                    dev_key[my_id] = synsets

        self.dev_key = dev_key
        self.test_key = test_key

    def convert_sense_key_to_synset(self, sense_key):
        '''
        Convert a WordNet sense key (e.g., 'group%1:03:00::') to a synset name (e.g., 'group.n.01').
        '''
        try:
            synset = wn.lemma_from_key(sense_key).synset()
            return synset.name()  # Convert synset to its name format
        except:
            return None

    def to_ascii(self, s):
        return codecs.encode(s, 'ascii', 'ignore').decode('ascii')

    def get_dev_instances(self):
        self.dev_instances = {k: v for (k, v) in self.dev_instances.items() if k in self.dev_key}
        return self.dev_instances

    def get_test_instances(self):
        self.test_instances = {k: v for (k, v) in self.test_instances.items() if k in self.test_key}
        return self.test_instances

    def get_dev_key(self):
        return self.dev_key

    def get_test_key(self):
        return self.test_key


if __name__ == '__main__':
    data_f = 'multilingual-all-words.en.xml'
    key_f = 'wordnet.en.key'
    loader = WSDLoader(data_f, key_f)
    loader.load_instances()
    loader.load_key()
    test_instances = loader.get_test_instances()
    dev_instances = loader.get_dev_instances()

    print(len(dev_instances))
    print(len(test_instances))