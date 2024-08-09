@Test
    public void testFit() {
        Map<Integer, double[]> data = new HashMap<>();
        data.put(1, new double[] {2, 4, 1});
        data.put(2, new double[] {1, 8, 22});
        data.put(3, new double[] {4, 10, 100});
        data.put(4, new double[] {0, 22, 300});

        DatasetBuilder<Integer, double[]> datasetBuilder = new LocalDatasetBuilder<>(data, parts);

        NormalizationTrainer<Integer, double[]> standardizationTrainer = new NormalizationTrainer<>();

        NormalizationPreprocessor<Integer, double[]> preprocessor = standardizationTrainer.fit(
            datasetBuilder,
            (k, v) -> v
        );

        assertArrayEquals(new double[] {0, 4, 1}, preprocessor.getMin(), 1e-8);
        assertArrayEquals(new double[] {4, 22, 300}, preprocessor.getMax(), 1e-8);
    }