public <C extends Serializable, D extends AutoCloseable> Dataset<C, D> build(
        LearningEnvironmentBuilder envBuilder,
        PartitionContextBuilder<K, V, C> partCtxBuilder,
        PartitionDataBuilder<K, V, C, D> partDataBuilder,
        LearningEnvironment localLearningEnv);