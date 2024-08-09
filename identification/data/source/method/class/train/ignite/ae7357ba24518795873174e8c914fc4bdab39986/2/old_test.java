@Test
    public void testApply() {
        double[][] data = new double[][]{
            {2., 4., 1.},
            {1., 8., 22.},
            {4., 10., 100.},
            {0., 22., 300.}
        };

        NormalizationPreprocessor<Integer, double[]> preprocessor = new NormalizationPreprocessor<>(
            new double[] {0, 4, 1},
            new double[] {4, 22, 300},
            (k, v) -> v
        );

        double[][] standardData = new double[][]{
            {2. / 4, (4. - 4.) / 18.,  0.},
            {1. / 4, (8. - 4.) / 18.,  (22. - 1.) / 299.},
            {1.,     (10. - 4.) / 18., (100. - 1.) / 299.},
            {0.,     (22. - 4.) / 18., (300. - 1.) / 299.}
        };

       for (int i = 0; i < data.length; i++)
           assertArrayEquals(standardData[i], preprocessor.apply(i, data[i]), 1e-8);
    }