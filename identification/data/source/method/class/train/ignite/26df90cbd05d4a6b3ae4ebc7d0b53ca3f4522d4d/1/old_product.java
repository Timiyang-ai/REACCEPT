public PipelineMdl<K, V> fit(DatasetBuilder datasetBuilder) {
        assert lbExtractor != null;
        assert featureExtractor != null;

        if (finalStage == null)
            throw new IllegalStateException("The Pipeline should be finished with the Training Stage.");

        // Reload for new fit
        finalFeatureExtractor = featureExtractor;

        preprocessingTrainers.forEach(e -> {

            finalFeatureExtractor = e.fit(
                envBuilder,
                datasetBuilder,
                finalFeatureExtractor
            );
        });

        //TODO: IGNITE-11481
        IgniteModel<Vector, Double> internalMdl = finalStage
            .fit(
                datasetBuilder,
                finalFeatureExtractor,
                lbExtractor
            );

        return new PipelineMdl<K, V>()
            .withFeatureExtractor(finalFeatureExtractor)
            .withLabelExtractor(lbExtractor)
            .withInternalMdl(internalMdl);
    }