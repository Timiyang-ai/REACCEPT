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

        RandomForestClassifierTrainer trainer = new RandomForestClassifierTrainer(4, 3, 5, 0.3, 4, 0.1);

        ModelsComposition mdl = trainer.fit(sample, parts, (k, v) -> VectorUtils.of(k), (k, v) -> v);

        mdl.getModels().forEach(m -> {
            assertTrue(m instanceof ModelOnFeaturesSubspace);
            assertTrue(((ModelOnFeaturesSubspace) m).getMdl() instanceof DecisionTreeConditionalNode);
        });

        assertTrue(mdl.getPredictionsAggregator() instanceof OnMajorityPredictionsAggregator);
        assertEquals(5, mdl.getModels().size());
    }