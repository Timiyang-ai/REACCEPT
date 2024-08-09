@Override
    public Map<String, Double> extract(final String text) {
        //initialize/reset the protected variables
        ID2word = new HashMap<>();
        ID2occurrences = new HashMap<>();
        position2ID = new LinkedHashMap<>(); //maintain the order of insertation
        numberOfWordsInDoc = 0;
        
        
        buildInternalArrays(text);
        
        Map<String, Double> keywordProximityScores = new HashMap<>();
        
        //move the "window" across the document by 1 word at each time
        for(Map.Entry<Integer, Integer> entry : position2ID.entrySet()) {
            Integer wordID = entry.getValue();
            if(!useThisWord(wordID)) {
                continue;
            }
            
            Integer position = entry.getKey();
            
            Map<String, Integer> wordCombinations = getCombinationsWithinWindow(position, parameters.getMaxCombinations());
            
            //translate positions to proximity metrics
            for(Map.Entry<String, Integer> entry2 : wordCombinations.entrySet()) {
                String IDcombinationReverse = entry2.getKey();
                Integer wordsBetween = entry2.getValue();
                
                int numberOfWords = PHPfunctions.substr_count(IDcombinationReverse, SEPARATOR)-1;//starts enumeration from 0. We need to subtract one because at the end each string has an extra SEPARATOR
                
                int extraWords = wordsBetween - numberOfWords;
                
                Double proximityScore = keywordProximityScores.get(IDcombinationReverse);
                if(proximityScore==null) {
                    proximityScore=0.0;
                }
                
                if(extraWords<=0) {
                    ++proximityScore;
                }
                else {
                    proximityScore+=0.5*extraWords;
                }
                
                keywordProximityScores.put(IDcombinationReverse, proximityScore);
            }
            
            
            wordCombinations = null;
        }
        
        //initialize keyword map
        Map<String, Double> keywordsMap = new HashMap<>();
        for(Map.Entry<String, Double> entry : keywordProximityScores.entrySet()) {
            Double proximityScore = entry.getValue();
            if(proximityScore>=parameters.getMinWordOccurrence()) {
                String IDcombinationReverse = entry.getKey();
                
                String[] listOfWordIDsReverse = StringUtils.split(IDcombinationReverse, SEPARATOR);
                
                StringBuilder sb = new StringBuilder(listOfWordIDsReverse.length*6);
                for(int i=listOfWordIDsReverse.length-1;i>=0;--i) {
                    Integer ID = Integer.valueOf(listOfWordIDsReverse[i]);
                    sb.append(ID2word.get(ID)).append(" ");
                }
                
                if(sb.length()>0) {
                    keywordsMap.put(sb.toString().trim(), proximityScore);
                }
                
                sb=null;
            }
        }
        keywordProximityScores=null;
        
        return keywordsMap;
    }