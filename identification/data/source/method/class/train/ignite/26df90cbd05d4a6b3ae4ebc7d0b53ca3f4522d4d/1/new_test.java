@Test
    public void testFit() {
        int sampleSize = 1000;
        Map<Double, LabeledVector<Double>> sample = new HashMap<>();
        for (int i = 0; i < sampleSize; i++) {
            double x1 = i;
            double x2 = x1 / 10.0;
            double x3 = x2 / 10.0;
            double x4 = x3 / 10.0;

            sample.put(x1 * x2 + x3 * x4, VectorUtils.of(x1, x2, x3, x4).labeled((double) i % 2));
        }

        ArrayList<FeatureMeta> meta = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            meta.add(new FeatureMeta("", i, false));
        RandomForestRegressionTrainer trainer = new RandomForestRegressionTrainer(meta)
            .withAmountOfTrees(5)
            .withFeaturesCountSelectionStrgy(x -> 2);

        ModelsComposition mdl = trainer.fit(sample, parts, new LabeledDummyVectorizer<>());
        assertTrue(mdl.getPredictionsAggregator() instanceof MeanValuePredictionsAggregator);
        assertEquals(5, mdl.getModels().size());
    }