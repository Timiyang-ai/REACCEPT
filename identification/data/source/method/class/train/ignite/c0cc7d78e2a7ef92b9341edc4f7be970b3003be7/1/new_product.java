@Override public <K, V> ModelsComposition fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, double[]> featureExtractor,
        IgniteBiFunction<K, V, Double> lbExtractor) {

        List<ModelOnFeaturesSubspace> learnedModels = new ArrayList<>();
        for (int i = 0; i < ensembleSize; i++)
            learnedModels.add(learnModel(datasetBuilder, featureExtractor, lbExtractor));

        return new ModelsComposition(learnedModels, predictionsAggregator);
    }