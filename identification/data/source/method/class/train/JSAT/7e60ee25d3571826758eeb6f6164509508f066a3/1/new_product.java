protected final void setUp(DataSet dataSet, Set<Integer> categoricalToRemove, Set<Integer> numericalToRemove)
    {
        for(int i : categoricalToRemove)
            if (i >= dataSet.getNumCategoricalVars())
                throw new RuntimeException("The data set does not have a categorical value " + i + " to remove");
        for(int i : numericalToRemove)
            if (i >= dataSet.getNumNumericalVars())
                throw new RuntimeException("The data set does not have a numercal value " + i + " to remove");
        
        catIndexMap = new int[dataSet.getNumCategoricalVars()-categoricalToRemove.size()];
        newCatHeader = new CategoricalData[catIndexMap.length];
        numIndexMap = new int[dataSet.getNumNumericalVars()-numericalToRemove.size()];
        int k = 0;
        for(int i = 0; i < dataSet.getNumCategoricalVars(); i++)
        {
            if(categoricalToRemove.contains(i))
                continue;
            newCatHeader[k] = dataSet.getCategories()[i].clone();
            catIndexMap[k++] = i;
        }
        k = 0;
        for(int i = 0; i < dataSet.getNumNumericalVars(); i++)
        {
            if(numericalToRemove.contains(i))
                continue;
            numIndexMap[k++] = i;
        }
    }