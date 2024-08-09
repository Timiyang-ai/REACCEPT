@Override
    public Map<String, Double> extract(final String text) {
        Map<Integer, String> ID2word = new HashMap<>(); //ID=>Kwd
        Map<Integer, Double> ID2occurrences = new HashMap<>(); //ID=>counts/scores
        Map<Integer, Integer> position2ID = new LinkedHashMap<>(); //word position=>ID maintain the order of insertation
        
        int numberOfWordsInDoc = buildInternalArrays(text, ID2word, ID2occurrences, position2ID);
        
        int maxCombinations = parameters.getMaxCombinations();
        Map<String, Double> keywordsMap = new HashMap<>();
        //move the "window" across the document by 1 word at each time
        for(Map.Entry<Integer, Integer> entry : position2ID.entrySet()) {
            Integer wordID = entry.getValue();
            if(!useThisWord(wordID, ID2word, ID2occurrences)) {
                continue;
            }
            
            Integer position = entry.getKey();
            //Build all the combinations of the current word with other words within the window and estimate the scores
            Map<LinkedList<Integer>, Double> positionCombinationsWithScores = getPositionCombinationsWithinWindow(position, maxCombinations, ID2word, ID2occurrences, position2ID, numberOfWordsInDoc);
            
            //Convert the positions into words and aggregate over their scores.
            for(Map.Entry<LinkedList<Integer>, Double> entry2 : positionCombinationsWithScores.entrySet()) {
                LinkedList<Integer> positionCombination = entry2.getKey();
                
                StringBuilder sb = new StringBuilder(positionCombination.size()*6);
                for(Integer pos : positionCombination) {
                    sb.append(ID2word.get(position2ID.get(pos))).append(" ");
                }
                
                if(sb.length()>0) {
                    String key = sb.toString().trim();
                    double score = entry2.getValue();
                    
                    keywordsMap.put(key, keywordsMap.getOrDefault(key, 0.0)+score);
                }
                
            }
        }
        
        //remove any word that has score less than the min occurrence
        double minScore = parameters.getMinWordOccurrence();
        Iterator<Map.Entry<String, Double>> it = keywordsMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            if(entry.getValue()<minScore) {
                it.remove();
            }
        }
        
        return keywordsMap;
    }