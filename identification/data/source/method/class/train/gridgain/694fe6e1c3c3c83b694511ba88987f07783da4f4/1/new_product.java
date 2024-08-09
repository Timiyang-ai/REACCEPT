public <K, O extends Serializable, S extends Serializable> RecommendationModel<O, S> fit(
        DatasetBuilder<K, ? extends ObjectSubjectRatingTriplet<O, S>> datasetBuilder) {
        try (Dataset<EmptyContext, RecommendationDatasetData<O, S>> dataset = datasetBuilder.build(
            environmentBuilder,
            new EmptyContextBuilder<>(),
            new RecommendationDatasetDataBuilder<>(),
            trainerEnvironment
        )) {
            return train(dataset);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }