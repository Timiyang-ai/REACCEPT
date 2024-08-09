@Override public <K, V> DecisionTreeNode fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, Vector> featureExtractor, IgniteBiFunction<K, V, Double> lbExtractor) {
        try (Dataset<EmptyContext, DecisionTreeData> dataset = datasetBuilder.build(
            new EmptyContextBuilder<>(),
            new DecisionTreeDataBuilder<>(featureExtractor, lbExtractor, usingIdx)
        )) {
            return fit(dataset);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }