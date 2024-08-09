@Override public <K, V> ModelsComposition fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, double[]> featureExtractor,
        IgniteBiFunction<K, V, Double> lbExtractor) {

        List<ModelsComposition.ModelOnFeaturesSubspace> learnedModels = new ArrayList<>();
        List<Future<ModelsComposition.ModelOnFeaturesSubspace>> futures = new ArrayList<>();

        for (int i = 0; i < ensembleSize; i++) {
            if (threadPool == null)
                learnedModels.add(learnModel(datasetBuilder, featureExtractor, lbExtractor));
            else {
                Future<ModelsComposition.ModelOnFeaturesSubspace> fut = threadPool.submit(() -> {
                    return learnModel(datasetBuilder, featureExtractor, lbExtractor);
                });

                futures.add(fut);
            }
        }

        if (threadPool != null) {
            for (Future<ModelsComposition.ModelOnFeaturesSubspace> future : futures) {
                try {
                    learnedModels.add(future.get());
                }
                catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return new ModelsComposition(learnedModels, predictionsAggregator);
    }