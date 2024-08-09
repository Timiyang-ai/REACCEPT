public default <T extends MLLogger.Factory & Serializable> LearningEnvironmentBuilder withLoggingFactory(T loggingFactory) {
        return withLoggingFactoryDependency(constant(loggingFactory));
    }