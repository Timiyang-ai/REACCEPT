@Test
    public void testFit() {
        Map<Integer, String[]> data = new HashMap<>();
        data.put(1, new String[] {"Monday", "September"});
        data.put(2, new String[] {"Monday", "August"});
        data.put(3, new String[] {"Monday", "August"});
        data.put(4, new String[] {"Friday", "June"});
        data.put(5, new String[] {"Friday", "June"});
        data.put(6, new String[] {"Sunday", "August"});

        DatasetBuilder<Integer, String[]> datasetBuilder = new LocalDatasetBuilder<>(data, parts);

        StringEncoderTrainer<Integer, String[]> strEncoderTrainer = new StringEncoderTrainer<>();

        StringEncoderPreprocessor<Integer, String[]> preprocessor = strEncoderTrainer.fit(
            datasetBuilder,
            (k, v) -> v
        );

        assertArrayEquals(new double[] {0.0, 2.0}, preprocessor.apply(7, new String[] {"Monday", "September"}), 1e-8);
    }