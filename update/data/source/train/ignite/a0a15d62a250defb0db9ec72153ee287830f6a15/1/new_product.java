@Override public <K, V, C extends Serializable> IgniteModel<I, O> update(IgniteModel<I, O> mdl,
                DatasetBuilder<K, V> datasetBuilder,
                Vectorizer<K, V, C, L> extractor) {
                DatasetTrainer<IgniteModel<I, O>, L> trainer1 = (DatasetTrainer<IgniteModel<I, O>, L>)trainer;
                return trainer1.update(mdl, datasetBuilder, extractor);
            }