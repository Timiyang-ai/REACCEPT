@Override public BinarizationPreprocessor<K, V> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, double[]> basePreprocessor) {
        return new BinarizationPreprocessor<>(threshold, basePreprocessor);
    }