public default DatasetBuilder<Vector, Double> asDatasetBuilder(int datasetSize, IgniteBiPredicate<Vector, Double> filter,
        int partitions, UpstreamTransformerBuilder upstreamTransformerBuilder) {

        return new DatasetBuilderAdapter(this, datasetSize, filter, partitions, upstreamTransformerBuilder);
    }