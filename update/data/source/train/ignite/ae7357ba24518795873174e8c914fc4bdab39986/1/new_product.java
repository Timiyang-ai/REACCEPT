@Override public NormalizationPreprocessor<K, V> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, double[]> basePreprocessor) {
        return new NormalizationPreprocessor<>(p, basePreprocessor);
    }