@Test
    public void withParallelismStrategy() {
        assertTrue(LearningEnvironment.builder().withParallelismStrategy(NoParallelismStrategy.INSTANCE).build()
            .parallelismStrategy() instanceof NoParallelismStrategy);

        assertTrue(LearningEnvironment.builder().withParallelismStrategy(new DefaultParallelismStrategy()).build()
            .parallelismStrategy() instanceof DefaultParallelismStrategy);
    }