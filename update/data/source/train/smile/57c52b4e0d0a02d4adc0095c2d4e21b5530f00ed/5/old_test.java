@Test
    public void testExtract() throws FileNotFoundException {
        System.out.println("extract");
        Scanner scanner = new Scanner(smile.data.parser.IOUtils.getTestDataReader("text/turing.txt"));
        String text = scanner.useDelimiter("\\Z").next();
        scanner.close();
        
        PorterStemmer stemmer = new PorterStemmer();
        SimpleTokenizer tokenizer = new SimpleTokenizer();
        ArrayList<String[]> sentences = new ArrayList<String[]>();
        for (String paragraph : SimpleParagraphSplitter.getInstance().split(text)) {
            for (String s : SimpleSentenceSplitter.getInstance().split(paragraph)) {
                String[] sentence = tokenizer.split(s);
                for (int i = 0; i < sentence.length; i++) {
                    sentence[i] = stemmer.stripPluralParticiple(sentence[i]).toLowerCase();
                }
                sentences.add(sentence);
            }
        }

        AprioriPhraseExtractor instance = new AprioriPhraseExtractor();
        ArrayList<ArrayList<NGram>> result = instance.extract(sentences, 4, 4);

        assertEquals(5, result.size());
        for (ArrayList<NGram> ngrams : result) {
        	for (NGram ngram : ngrams) {
                System.out.print(ngram);
        	}
        	System.out.println();
        }
    }