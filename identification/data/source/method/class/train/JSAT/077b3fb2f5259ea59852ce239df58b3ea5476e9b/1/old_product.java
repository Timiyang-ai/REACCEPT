public static RegressionDataSet loadR(Reader reader, double sparseRatio, int vectorLength) throws IOException
    {
        return (RegressionDataSet) loadG(reader, sparseRatio, vectorLength, false);
    }