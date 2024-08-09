@Test
    public void testFit() {
        Map<Integer, Vector> data = new HashMap<>();
        data.put(1, VectorUtils.of(1, 2, Double.NaN));
        data.put(2, VectorUtils.of(1, Double.NaN, 22));
        data.put(3, VectorUtils.of(Double.NaN, 10, 100));
        data.put(4, VectorUtils.of(0, 2, 100));

        DatasetBuilder<Integer, Vector> datasetBuilder = new LocalDatasetBuilder<>(data, parts);

        final Vectorizer<Integer, Vector, Integer, Double> vectorizer = new DummyVectorizer<>(0, 1, 2);

        ImputerTrainer<Integer, Vector> imputerTrainer = new ImputerTrainer<Integer, Vector>()
            .withImputingStrategy(ImputingStrategy.MOST_FREQUENT);

        ImputerPreprocessor<Integer, Vector> preprocessor = imputerTrainer.fit(
            TestUtils.testEnvBuilder(),
            datasetBuilder,
            vectorizer
        );

        assertArrayEquals(new double[] {1, 0, 100}, preprocessor.apply(5, VectorUtils.of(Double.NaN, 0, Double.NaN)).features().asArray(), 1e-8);
    }