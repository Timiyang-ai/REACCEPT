@Test
    public void testUpdate() {
        Map<Integer, double[]> cacheMock = new HashMap<>();

        for (int i = 0; i < twoClusters.length; i++)
            cacheMock.put(i, twoClusters[i]);

        ANNClassificationTrainer trainer = new ANNClassificationTrainer()
            .withK(10)
            .withMaxIterations(10)
            .withEpsilon(1e-4)
            .withDistance(new EuclideanDistance());

        ANNClassificationModel originalMdl = (ANNClassificationModel) trainer.withSeed(1234L).fit(
            cacheMock,
            parts,
            (k, v) -> VectorUtils.of(Arrays.copyOfRange(v, 1, v.length)),
            (k, v) -> v[0]
        ).withK(3)
            .withDistanceMeasure(new EuclideanDistance())
            .withStrategy(NNStrategy.SIMPLE);

        ANNClassificationModel updatedOnSameDataset = (ANNClassificationModel) trainer.withSeed(1234L).update(originalMdl,
            cacheMock, parts,
            (k, v) -> VectorUtils.of(Arrays.copyOfRange(v, 0, v.length - 1)),
            (k, v) -> v[2]
        ).withK(3)
            .withDistanceMeasure(new EuclideanDistance())
            .withStrategy(NNStrategy.SIMPLE);

        ANNClassificationModel updatedOnEmptyDataset = (ANNClassificationModel) trainer.withSeed(1234L).update(originalMdl,
            new HashMap<Integer, double[]>(), parts,
            (k, v) -> VectorUtils.of(Arrays.copyOfRange(v, 0, v.length - 1)),
            (k, v) -> v[2]
        ).withK(3)
            .withDistanceMeasure(new EuclideanDistance())
            .withStrategy(NNStrategy.SIMPLE);

        Vector v1 = VectorUtils.of(550, 550);
        Vector v2 = VectorUtils.of(-550, -550);
        TestUtils.assertEquals(originalMdl.apply(v1), updatedOnSameDataset.apply(v1), PRECISION);
        TestUtils.assertEquals(originalMdl.apply(v2), updatedOnSameDataset.apply(v2), PRECISION);
        TestUtils.assertEquals(originalMdl.apply(v1), updatedOnEmptyDataset.apply(v1), PRECISION);
        TestUtils.assertEquals(originalMdl.apply(v2), updatedOnEmptyDataset.apply(v2), PRECISION);
    }