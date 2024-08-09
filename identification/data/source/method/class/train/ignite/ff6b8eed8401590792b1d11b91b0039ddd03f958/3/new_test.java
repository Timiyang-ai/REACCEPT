@Test
    public void withLoggingFactory() {
        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withLoggingFactoryDependency(part -> ConsoleLogger.factory(MLLogger.VerboseLevel.HIGH))
            .buildForTrainer().logger() instanceof ConsoleLogger);

        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withLoggingFactoryDependency(part -> ConsoleLogger.factory(MLLogger.VerboseLevel.HIGH))
            .buildForTrainer().logger(this.getClass()) instanceof ConsoleLogger);

        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withLoggingFactoryDependency(part -> NoOpLogger.factory())
            .buildForTrainer().logger() instanceof NoOpLogger);

        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withLoggingFactoryDependency(part -> NoOpLogger.factory())
            .buildForTrainer().logger(this.getClass()) instanceof NoOpLogger);

        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withLoggingFactoryDependency(part -> CustomMLLogger.factory(new NullLogger()))
            .buildForTrainer().logger() instanceof CustomMLLogger);

        assertTrue(LearningEnvironmentBuilder.defaultBuilder().withLoggingFactoryDependency(part -> CustomMLLogger.factory(new NullLogger()))
            .buildForTrainer().logger(this.getClass()) instanceof CustomMLLogger);
    }