@Override public <K, V> IgniteModel<I, O> update(IgniteModel<I, O> mdl, DatasetBuilder<K, V> datasetBuilder,
                FeatureLabelExtractor<K, V, L> extractor) {
                DatasetTrainer<IgniteModel<I, O>, L> trainer1 = (DatasetTrainer<IgniteModel<I, O>, L>)trainer;
                return trainer1.update(mdl, datasetBuilder, extractor);
            }