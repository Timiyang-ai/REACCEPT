public static double nBar(TransposeDataList clusterIdList) {
        int populationM = clusterIdList.size();
        
        double nBar = 0.0;
        for(Map.Entry<Object, FlatDataList> entry : clusterIdList.entrySet()) {
            nBar += (double)entry.getValue().size()/populationM;
        }

        return nBar;
    }