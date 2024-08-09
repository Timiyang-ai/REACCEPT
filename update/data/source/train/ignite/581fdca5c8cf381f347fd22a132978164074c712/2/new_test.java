@Test
    public void testFit() {
        int sampleSize = 1000;
        Map<double[], Double> sample = new HashMap<>();
        for (int i = 0; i < sampleSize; i++) {
            double x1 = i;
            double x2 = x1 / 10.0;
            double x3 = x2 / 10.0;
            double x4 = x3 / 10.0;

            sample.put(new double[] {x1, x2, x3, x4}, (double)(i % 2));
        }

        ArrayList<FeatureMeta> meta = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            meta.add(new FeatureMeta("", i, false));
        DatasetTrainer<ModelsComposition, Double> trainer = new RandomForestClassifierTrainer(meta)
            .withAmountOfTrees(5)
            .withFeaturesCountSelectionStrgy(x -> 2)
            .withEnvironmentBuilder(TestUtils.testEnvBuilder());

        ModelsComposition mdl = trainer.fit(sample, parts, (k, v) -> VectorUtils.of(k), (k, v) -> v);

        assertTrue(mdl.getPredictionsAggregator() instanceof OnMajorityPredictionsAggregator);
        assertEquals(5, mdl.getModels().size());
    }