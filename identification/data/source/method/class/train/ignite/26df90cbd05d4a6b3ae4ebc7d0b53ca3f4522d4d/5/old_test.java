@Test
    public void testFit() {
        int size = 100;

        CacheConfiguration<Integer, double[]> trainingSetCacheCfg = new CacheConfiguration<>();
        trainingSetCacheCfg.setAffinity(new RendezvousAffinityFunction(false, 10));
        trainingSetCacheCfg.setName("TRAINING_SET");

        IgniteCache<Integer, double[]> data = ignite.createCache(trainingSetCacheCfg);

        Random rnd = new Random(0);
        for (int i = 0; i < size; i++) {
            double x = rnd.nextDouble() - 0.5;
            data.put(i, new double[] {x, x > 0 ? 1 : 0});
        }

        ArrayList<FeatureMeta> meta = new ArrayList<>();
        meta.add(new FeatureMeta("", 0, false));
        RandomForestRegressionTrainer trainer = new RandomForestRegressionTrainer(meta)
            .withAmountOfTrees(5)
            .withFeaturesCountSelectionStrgy(x -> 2);

        ModelsComposition mdl = trainer.fit(ignite, data, new ArraysVectorizer<Integer>().labeled(1));

        assertTrue(mdl.getPredictionsAggregator() instanceof MeanValuePredictionsAggregator);
        assertEquals(5, mdl.getModels().size());
    }