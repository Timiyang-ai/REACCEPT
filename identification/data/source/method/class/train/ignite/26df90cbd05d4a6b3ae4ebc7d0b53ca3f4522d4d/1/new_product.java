public PipelineMdl<K, V> fit(DatasetBuilder datasetBuilder) {
        if (finalStage == null)
            throw new IllegalStateException("The Pipeline should be finished with the Training Stage.");

        // Reload for new fit
        finalPreprocessor = vectorizer;

        preprocessingTrainers.forEach(e -> {

            finalPreprocessor = e.fit(
                envBuilder,
                datasetBuilder,
                finalPreprocessor
            );
        });

        IgniteModel<Vector, Double> internalMdl = finalStage
            .fit(
                datasetBuilder,
                finalPreprocessor
            );

        return new PipelineMdl<K, V>()
            .withPreprocessor(finalPreprocessor)
            .withInternalMdl(internalMdl);
    }