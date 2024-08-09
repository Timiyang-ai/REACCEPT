public ArrayList<ArrayList<NGram>> extract(Collection<String[]> sentences, int maxNGramSize, int minFrequency) {
        ArrayList<Set<NGram>> features = new ArrayList<Set<NGram>>(maxNGramSize + 1);
        features.add(new HashSet<NGram>());
        for (int n = 1; n <= maxNGramSize; n++) {
            Map<NGram, Integer> candidates = new HashMap<NGram, Integer>();
            Set<NGram> feature = new HashSet<NGram>();
            features.add(feature);
            Set<NGram> feature_1 = features.get(n - 1);
            for (String[] sentence : sentences) {
                for (int i = 0; i <= sentence.length - n; i++) {
                    NGram ngram  = new NGram(Arrays.copyOfRange(sentence, i, i+n));
                    boolean add = false;
                    if (n == 1) {
                        add = true;
                    } else {
                        NGram initialGram  = new NGram(Arrays.copyOfRange(sentence, i, i+n-1));
                        NGram finalGram  = new NGram(Arrays.copyOfRange(sentence, i+1, i+n));
                        if (feature_1.contains(initialGram) && feature_1.contains(finalGram)) {
                            add = true;
                        }
                    }
                    
                    if (add) {
                        if (candidates.containsKey(ngram)) {
                            candidates.put(ngram, candidates.get(ngram) + 1);
                        } else {
                            candidates.put(ngram, 1);
                        }
                    }
                }
            }
            
            for (Map.Entry<NGram, Integer> entry : candidates.entrySet()) {
                if (entry.getValue() >= minFrequency) {
                    NGram ngram = entry.getKey();
                    if (ngram.words.length == 1 && EnglishPunctuations.getInstance().contains(ngram.words[0])) {
                        continue;
                    }
                    
                    ngram.freq = entry.getValue();
                    feature.add(ngram);
                }
            }
        }
        
        // filter out stop words
        ArrayList<ArrayList<NGram>> results = new ArrayList<ArrayList<NGram>>();
        for (Set<NGram> ngrams : features) {
            ArrayList<NGram> result = new ArrayList<NGram>();
            results.add(result);
            for (NGram ngram : ngrams) {
                boolean stopWord = true;
                if (!EnglishStopWords.DEFAULT.contains(ngram.words[0]) && !EnglishStopWords.DEFAULT.contains(ngram.words[ngram.words.length-1])) {
                    for (String word : ngram.words) {
                        if (!EnglishStopWords.DEFAULT.contains(word)) {
                            stopWord = false;
                            break;
                        }
                    }
                }

                if (!stopWord) {
                    result.add(ngram);
                }
            }
            
            Collections.sort(result);
            Collections.reverse(result);
        }
        
        return results;
    }