public default <T extends ParallelismStrategy & Serializable> LearningEnvironmentBuilder withParallelismStrategy(T stgy) {
        return withParallelismStrategyDependency(constant(stgy));
    }