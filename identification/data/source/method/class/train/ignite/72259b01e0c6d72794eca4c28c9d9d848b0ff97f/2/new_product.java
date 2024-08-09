public IgniteBiFunction<K, V, R> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, T> basePreprocessor);