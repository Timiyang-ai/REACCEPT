@Override public <K, V, C extends Serializable> MultiClassModel<M> fit(DatasetBuilder<K, V> datasetBuilder,
        Vectorizer<K, V, C, Double> extractor) {

        return updateModel(null, datasetBuilder, extractor);
    }