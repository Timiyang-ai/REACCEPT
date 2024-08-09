public static double Nbar(TransposeDataList clusterIdList) {
        int populationM = clusterIdList.size();
        
        double Nbar = 0.0;
        for(Map.Entry<Object, FlatDataList> entry : clusterIdList.entrySet()) {
            Nbar += (double)entry.getValue().size()/populationM;
        }

        return Nbar;
    }