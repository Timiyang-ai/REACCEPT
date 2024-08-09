@Test
    public void testFit() {
        int size = 100;
        Random rnd = new Random(0L);
        Double[][] ratings = new Double[size][size];
        // Quadrant I contains "0", quadrant II contains "1", quadrant III contains "0", quadrant IV contains "1".
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (rnd.nextBoolean())
                    ratings[i][j] = ((i > size / 2) ^ (j > size / 2)) ? 1.0 : 0.0;
            }
        }

        int seq = 0;
        Map<Integer, ObjectSubjectRatingTriplet<Integer, Integer>> data = new HashMap<>();
        for (ObjectSubjectRatingTriplet<Integer, Integer> triplet : toList(ratings))
            data.put(seq++, triplet);

        RecommendationTrainer trainer = new RecommendationTrainer()
            .withMaxIterations(100)
            .withLearningRate(25.0)
            .withBatchSize(100)
            .withK(2)
            .withLearningEnvironmentBuilder(LearningEnvironmentBuilder.defaultBuilder().withRNGSeed(1))
            .withTrainerEnvironment(LearningEnvironmentBuilder.defaultBuilder().withRNGSeed(1).buildForTrainer());

        RecommendationModel<Integer, Integer> mdl = trainer.fit(new LocalDatasetBuilder<>(data, 1));

        int incorrect = 0;
        for (ObjectSubjectRatingTriplet<Integer, Integer> triplet : toList(ratings)) {
            double prediction = Math.round(mdl.predict(triplet));
            if (Math.abs(prediction - triplet.getRating()) >= 1e-5) incorrect++;
        }

        assertEquals(0, incorrect);
    }