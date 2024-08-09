@Test
    public void testFit() {
        Map<Integer, double[]> data = new HashMap<>();
        data.put(1, new double[] {2, 4, 1});
        data.put(2, new double[] {1, 8, 22});
        data.put(3, new double[] {4, 10, 100});
        data.put(4, new double[] {0, 22, 300});

        DatasetBuilder<Integer, double[]> datasetBuilder = new LocalDatasetBuilder<>(data, parts);

        NormalizationTrainer<Integer, double[]> normalizationTrainer = new NormalizationTrainer<Integer, double[]>()
            .withP(3);

        NormalizationPreprocessor<Integer, double[]> preprocessor = normalizationTrainer.fit(
            datasetBuilder,
            (k, v) -> v
        );

        assertArrayEquals(new double[] {0.125, 0.99, 0.125}, preprocessor.apply(5, new double[] {1, 8, 1}), 1e-2);
    }