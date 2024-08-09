public static TransposeDataCollection weightedProbabilitySampling(AssociativeArray2D strataFrequencyTable, AssociativeArray nh, boolean withReplacement) {
        TransposeDataCollection sampledIds = new TransposeDataCollection(); 
    
        for(Map.Entry<Object, AssociativeArray> entry : strataFrequencyTable.entrySet()) {
            Object strata = entry.getKey();
            
            Number sampleN =  ((Number)nh.get(strata));
            if(sampleN==null) {
                continue;
            }
            
            sampledIds.put(strata, SRS.weightedProbabilitySampling(entry.getValue(), sampleN.intValue(), withReplacement));
        }
        
        return sampledIds;
    }