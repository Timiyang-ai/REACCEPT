@Test
    public void testFit() {
        GmmTrainer trainer = new GmmTrainer(2, 1)
            .withInitialMeans(Arrays.asList(
                VectorUtils.of(1.0, 2.0),
                VectorUtils.of(-1.0, -2.0)));

        GmmModel mdl = trainer.fit(
            new LocalDatasetBuilder<>(data, parts),
            new DoubleArrayVectorizer<Integer>().labeled(Vectorizer.LabelCoordinate.LAST)
        );

        Assert.assertEquals(2, mdl.countOfComponents());
        Assert.assertEquals(2, mdl.dimension());
        Assert.assertArrayEquals(new double[] {1.33, 1.33}, mdl.distributions().get(0).mean().asArray(), 1e-2);
        Assert.assertArrayEquals(new double[] {-1.33, -1.33}, mdl.distributions().get(1).mean().asArray(), 1e-2);
    }