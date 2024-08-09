public <C extends Serializable, D extends AutoCloseable> Dataset<C, D> build(
        PartitionContextBuilder<K, V, C> partCtxBuilder, PartitionDataBuilder<K, V, C, D> partDataBuilder);