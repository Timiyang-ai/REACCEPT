@Test
    public void testFit() {
        Map<Integer, double[]> data = new HashMap<>();
        data.put(1, new double[] {2, 4, 1});
        data.put(2, new double[] {1, 8, 22});
        data.put(3, new double[] {4, 10, 100});
        data.put(4, new double[] {0, 22, 300});

        DatasetBuilder<Integer, double[]> datasetBuilder = new LocalDatasetBuilder<>(data, parts);

        BinarizationTrainer<Integer, double[]> binarizationTrainer = new BinarizationTrainer<Integer, double[]>()
            .withThreshold(10);

        assertEquals(10., binarizationTrainer.getThreshold(), 0);

        BinarizationPreprocessor<Integer, double[]> preprocessor = binarizationTrainer.fit(
            datasetBuilder,
            (k, v) -> VectorUtils.of(v)
        );

        assertEquals(binarizationTrainer.getThreshold(), preprocessor.getThreshold(), 0);

        assertArrayEquals(new double[] {0, 0, 1}, preprocessor.apply(5, new double[] {1, 10, 100}).asArray(), 1e-8);
    }