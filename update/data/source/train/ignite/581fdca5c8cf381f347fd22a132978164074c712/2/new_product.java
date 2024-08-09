@Override public <K, V> KMeansModel fit(DatasetBuilder<K, V> datasetBuilder,
        FeatureLabelExtractor<K, V, Double> extractor) {
        return updateModel(null, datasetBuilder, extractor);
    }