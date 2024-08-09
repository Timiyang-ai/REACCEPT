@Test
    public void withParallelismStrategy() {
        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withParallelismStrategyDependency(part -> NoParallelismStrategy.INSTANCE)
            .buildForTrainer()
            .parallelismStrategy() instanceof NoParallelismStrategy);

        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withParallelismStrategyDependency(part -> new DefaultParallelismStrategy())
            .buildForTrainer()
            .parallelismStrategy() instanceof DefaultParallelismStrategy);
    }