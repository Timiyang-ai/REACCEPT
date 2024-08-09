public static RegressionDataSet loadR(Reader reader, double sparseRatio, int vectorLength) throws IOException
    {
        return loadR(reader, sparseRatio, vectorLength, DataStore.DEFAULT_STORE);
    }