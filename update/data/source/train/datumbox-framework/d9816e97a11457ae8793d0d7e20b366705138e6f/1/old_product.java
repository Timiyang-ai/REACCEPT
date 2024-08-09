public static boolean test(TransposeDataList transposeDataList, boolean is_twoTailed, double aLevel) throws IllegalArgumentException { 
        //Note! Despite the fact that this test seems very very easy, in fact it is SUPER HARD to calcuate.
        
        //validate the results of the test with the theory because the PHP to JAVA implementation changed a lot the logic!
        int k=transposeDataList.size();
        if(k!=2) {
            throw new IllegalArgumentException();
        }
        
        Object[] keys = transposeDataList.keySet().toArray();
        Set<Double> allUniqueValues = new TreeSet<>(); //sorted set
        for(int j=0;j<k;++j) {
            Iterator<Double> it = transposeDataList.get(keys[j]).iteratorDouble();
            while(it.hasNext()) {
                allUniqueValues.add(it.next());
            }
        }
        
        DataTable2D distributionAndValue2Probability = new DataTable2D();
        for(int j=0;j<k;++j) {
            Object keyj = keys[j];
            int nj=transposeDataList.get(keyj).size();
            if(nj<=0) {
                throw new IllegalArgumentException();
            }
            
            
            int rank = 1;
            for(Double value : allUniqueValues) {
                Object objValue = value;
                if(!transposeDataList.get(keyj).contains(objValue)) { //if this is a missing value
                    //add the probability that matches the previous rank
                    distributionAndValue2Probability.put2d(keyj, objValue, (rank-1.0)/nj);
                    continue;
                }
                
                if(distributionAndValue2Probability.get2d(keyj, objValue)==null) { //keeps the lowest probability in case of ties
                    distributionAndValue2Probability.put2d(keyj, objValue, (double)rank/nj);
                }
                ++rank;
            }
        }
        allUniqueValues=null;
        
        double maxDelta=0.0;
        for(Object key : distributionAndValue2Probability.get(keys[0]).keySet()) {
            //get the 2 probabilities from the 2 dataTables and find max delta
            
            double v1 = distributionAndValue2Probability.get(keys[0]).getDouble(key);
            double v2 = distributionAndValue2Probability.get(keys[1]).getDouble(key);
            
            double delta=Math.abs(v2-v1);
            if(delta>maxDelta) {
                maxDelta=delta;
            }   
        }
        distributionAndValue2Probability = null;

        int n1 = transposeDataList.get(keys[0]).size();
        int n2 = transposeDataList.get(keys[1]).size();
        keys = null;
        
        boolean rejectH0 = checkCriticalValue(maxDelta, is_twoTailed, n1, n2, aLevel);
        
        return rejectH0;
    }