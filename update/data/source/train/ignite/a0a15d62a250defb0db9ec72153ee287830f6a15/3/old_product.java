@Override public <K, V> MultiClassModel<M> fit(DatasetBuilder<K, V> datasetBuilder,
        FeatureLabelExtractor<K, V, Double> extractor) {

        return updateModel(null, datasetBuilder, extractor);
    }