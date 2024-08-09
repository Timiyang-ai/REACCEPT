private PipelineMdl<K, V> fit(DatasetBuilder datasetBuilder) {
        assert lbExtractor != null;
        assert finalFeatureExtractor != null;

        if (finalStage == null)
            throw new IllegalStateException("The Pipeline should be finished with the Training Stage.");

        preprocessors.forEach(e -> {

            finalFeatureExtractor = e.fit(
                envBuilder,
                datasetBuilder,
                finalFeatureExtractor
            );
        });

        Model<Vector, Double> internalMdl = finalStage
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