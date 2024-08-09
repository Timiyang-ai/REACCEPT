@Override public <K, V, C extends Serializable> GmmModel fit(DatasetBuilder<K, V> datasetBuilder,
        Vectorizer<K, V, C, Double> extractor) {
        return updateModel(null, datasetBuilder, extractor);
    }