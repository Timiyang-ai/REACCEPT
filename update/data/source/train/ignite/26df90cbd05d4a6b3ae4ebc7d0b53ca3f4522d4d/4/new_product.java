@Override public <K, V> GmmModel fit(DatasetBuilder<K, V> datasetBuilder,
        Preprocessor<K, V> extractor) {
        return updateModel(null, datasetBuilder, extractor);
    }