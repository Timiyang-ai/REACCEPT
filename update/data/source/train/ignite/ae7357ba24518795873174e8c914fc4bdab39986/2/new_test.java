@Test
    public void testApply() {
        double[][] data = new double[][]{
            {1, 2, 1},
            {1, 1, 1},
            {1, 0, 0},
        };

        NormalizationPreprocessor<Integer, double[]> preprocessor = new NormalizationPreprocessor<>(
            1,
            (k, v) -> v
        );

        double[][] postProcessedData = new double[][]{
            {0.25, 0.5, 0.25},
            {0.33, 0.33, 0.33},
            {1, 0, 0}
        };

       for (int i = 0; i < data.length; i++)
           assertArrayEquals(postProcessedData[i], preprocessor.apply(i, data[i]), 1e-2);
    }