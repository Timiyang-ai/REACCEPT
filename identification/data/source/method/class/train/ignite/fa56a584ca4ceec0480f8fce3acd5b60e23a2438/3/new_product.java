@Override public BinarizationPreprocessor<K, V> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, Vector> basePreprocessor) {
        return new BinarizationPreprocessor<>(threshold, basePreprocessor);
    }