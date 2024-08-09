@Test
    public void testApply() {
        double[][] data = new double[][]{
            {Double.NaN, 20, 3},
            {2, Double.NaN, 8},
            {Double.NaN, Double.NaN, Double.NaN},
        };

        ImputerPreprocessor<Integer, Vector> preprocessor = new ImputerPreprocessor<>(
            VectorUtils.of(1.1, 10.1, 100.1),
            (k, v) -> v
        );

        double[][] postProcessedData = new double[][]{
            {1.1, 20, 3},
            {2, 10.1, 8},
            {1.1, 10.1, 100.1},
        };

       for (int i = 0; i < data.length; i++)
           assertArrayEquals(postProcessedData[i], preprocessor.apply(i, VectorUtils.of(data[i])).asArray(), 1e-8);
    }