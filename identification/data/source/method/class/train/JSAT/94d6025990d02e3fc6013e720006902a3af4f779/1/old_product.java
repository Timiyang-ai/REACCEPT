public void trainC(ClassificationDataSet dataSet, Set<Integer> categoriesToUse)
    {
        if(categoriesToUse.size() > dataSet.getNumFeatures()+1)
            throw new FailedToFitException("CPT can not train on a number of features greater then the dataset's feature count. "
                    + "Specified " + categoriesToUse.size() + " but data set has only " + dataSet.getNumFeatures());
        CategoricalData[] tmp = dataSet.getCategories();
        predicting = dataSet.getPredicting();
        predictingIndex = dataSet.getNumCategoricalVars();
        valid = new HashMap<Integer, CategoricalData>();
        realIndexToCatIndex = new int[categoriesToUse.size()];
        catIndexToRealIndex = new int[dataSet.getNumCategoricalVars()+1];//+1 for the predicting
        Arrays.fill(catIndexToRealIndex, -1);//-1s are non existant values
        dimSize = new int[realIndexToCatIndex.length];
        int flatSize = 1;//The number of bins in the n dimensional array
        int k = 0;
        for(int i : categoriesToUse)
        {
            if(i == predictingIndex)//The predicint class is treated seperatly
                continue;
            CategoricalData dataInfo = tmp[i];
            flatSize *= dataInfo.getNumOfCategories();
            valid.put(i, dataInfo);
            realIndexToCatIndex[k] = i;
            catIndexToRealIndex[i] = k;
            dimSize[k++] = dataInfo.getNumOfCategories();
        }
        
        if(categoriesToUse.contains(predictingIndex))
        {
            //Lastly the predicing quantity
            flatSize *= predicting.getNumOfCategories();
            realIndexToCatIndex[k] = predictingIndex;
            catIndexToRealIndex[predictingIndex] = k;
            dimSize[k] = predicting.getNumOfCategories();
            valid.put(predictingIndex, predicting);
        }
        
        countArray = new double[flatSize];
        Arrays.fill(countArray, 1);//Laplace correction
        
        int[] cordinate = new int[dimSize.length];
        for(int i = 0; i < dataSet.size(); i++)
        {
            DataPoint dp = dataSet.getDataPoint(i);
            for (int j = 0; j < realIndexToCatIndex.length; j++)
                if (realIndexToCatIndex[j] != predictingIndex)
                    cordinate[j] = dp.getCategoricalValue(realIndexToCatIndex[j]);
                else
                    cordinate[j] = dataSet.getDataPointCategory(i);
            countArray[cordToIndex(cordinate)]+= dp.getWeight();
        }
    }