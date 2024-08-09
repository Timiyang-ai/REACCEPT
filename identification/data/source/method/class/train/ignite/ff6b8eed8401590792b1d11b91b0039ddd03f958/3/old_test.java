@Test
    public void withLoggingFactory() {
        assertTrue(LearningEnvironment.builder().withLoggingFactory(ConsoleLogger.factory(MLLogger.VerboseLevel.HIGH))
            .build().logger() instanceof ConsoleLogger);

        assertTrue(LearningEnvironment.builder().withLoggingFactory(ConsoleLogger.factory(MLLogger.VerboseLevel.HIGH))
            .build().logger(this.getClass()) instanceof ConsoleLogger);

        assertTrue(LearningEnvironment.builder().withLoggingFactory(NoOpLogger.factory())
            .build().logger() instanceof NoOpLogger);

        assertTrue(LearningEnvironment.builder().withLoggingFactory(NoOpLogger.factory())
            .build().logger(this.getClass()) instanceof NoOpLogger);

        assertTrue(LearningEnvironment.builder().withLoggingFactory(CustomMLLogger.factory(new NullLogger()))
            .build().logger() instanceof CustomMLLogger);

        assertTrue(LearningEnvironment.builder().withLoggingFactory(CustomMLLogger.factory(new NullLogger()))
            .build().logger(this.getClass()) instanceof CustomMLLogger);
    }