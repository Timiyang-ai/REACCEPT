@Test
    public void testBuild() throws ExecutionException, InterruptedException {
        AsyncInfModelBuilder mdlBuilder = new ThreadedInfModelBuilder(10);

        InfModel<Integer, Future<Integer>> infMdl = mdlBuilder.build(
            InfModelBuilderTestUtil.getReader(),
            InfModelBuilderTestUtil.getParser()
        );

        for (int i = 0; i < 100; i++)
            assertEquals(Integer.valueOf(i), infMdl.predict(i).get());
    }