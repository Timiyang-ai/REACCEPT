@Test
    public void testApply() {
        double[][] data = new double[][] {
            {2., 4., 1.},
            {1., 8., 22.},
            {-4., 10., 100.},
            {0., 22., 300.}
        };
        double[] maxAbs = new double[] {4, 22, 300};
        MaxAbsScalerPreprocessor<Integer, Vector> preprocessor = new MaxAbsScalerPreprocessor<>(
            maxAbs,
            (k, v) -> v
        );

        double[][] expData = new double[][] {
            {.5, 4. / 22, 1. / 300},
            {.25, 8. / 22, 22. / 300},
            {-1., 10. / 22, 100. / 300},
            {0., 22. / 22, 300. / 300}
        };

        for (int i = 0; i < data.length; i++)
            assertArrayEquals(expData[i], preprocessor.apply(i, VectorUtils.of(data[i])).asArray(), 1e-8);
    }