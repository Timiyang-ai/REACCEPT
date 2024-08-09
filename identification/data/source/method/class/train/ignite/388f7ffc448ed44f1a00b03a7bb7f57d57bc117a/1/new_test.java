@Test
    public void testUpdate() {
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
        RandomForestClassifierTrainer trainer = new RandomForestClassifierTrainer(meta)
            .withAmountOfTrees(100)
            .withFeaturesCountSelectionStrgy(x -> 2)
            .withEnvironmentBuilder(TestUtils.testEnvBuilder());

        ModelsComposition originalMdl = trainer.fit(sample, parts, (k, v) -> VectorUtils.of(k), (k, v) -> v);
        ModelsComposition updatedOnSameDS = trainer.update(originalMdl, sample, parts, (k, v) -> VectorUtils.of(k), (k, v) -> v);
        ModelsComposition updatedOnEmptyDS = trainer.update(originalMdl, new HashMap<double[], Double>(), parts, (k, v) -> VectorUtils.of(k), (k, v) -> v);

        Vector v = VectorUtils.of(5, 0.5, 0.05, 0.005);
        assertEquals(originalMdl.apply(v), updatedOnSameDS.apply(v), 0.01);
        assertEquals(originalMdl.apply(v), updatedOnEmptyDS.apply(v), 0.01);
    }