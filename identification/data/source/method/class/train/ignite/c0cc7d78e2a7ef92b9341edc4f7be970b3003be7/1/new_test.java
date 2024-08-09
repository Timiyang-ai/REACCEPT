@Test public void testFit() {
        int sampleSize = 1000;
        Map<Double, double[]> sample = new HashMap<>();
        for (int i = 0; i < sampleSize; i++) {
            double x1 = i;
            double x2 = x1 / 10.0;
            double x3 = x2 / 10.0;
            double x4 = x3 / 10.0;

            sample.put(x1 * x2 + x3 * x4, new double[] {x1, x2, x3, x4});
        }

        RandomForestRegressionTrainer trainer = new RandomForestRegressionTrainer(4, 3, 5, 0.3, 4, 0.1);
        ModelsComposition model = trainer.fit(sample, parts, (k, v) -> v, (k, v) -> k);
        model.getModels().forEach(m -> {
            assertTrue(m instanceof ModelOnFeaturesSubspace);
            assertTrue(((ModelOnFeaturesSubspace) m).getMdl() instanceof DecisionTreeConditionalNode);
        });

        assertTrue(model.getPredictionsAggregator() instanceof MeanValuePredictionsAggregator);
        assertEquals(5, model.getModels().size());
    }