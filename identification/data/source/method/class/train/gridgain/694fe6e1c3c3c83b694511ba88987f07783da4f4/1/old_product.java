public <K, O extends Serializable, S extends Serializable> RecommendationModel<O, S> fit(
        DatasetBuilder<K, ? extends ObjectSubjectRatingTriplet<O, S>> datasetBuilder) {
        try (Dataset<EmptyContext, RecommendationDatasetData<O, S>> dataset = datasetBuilder.build(
            environmentBuilder,
            new EmptyContextBuilder<>(),
            new RecommendationDatasetDataBuilder<>(),
            trainerEnvironment
        )) {
            // Collect total set of objects and subjects (their identifiers).
            Set<O> objects = dataset.compute(RecommendationDatasetData::getObjects, RecommendationTrainer::join);
            Set<S> subjects = dataset.compute(RecommendationDatasetData::getSubjects, RecommendationTrainer::join);

            // Generate initial model (object and subject matrices) initializing them with random values.
            Map<O, Vector> objMatrix = generateRandomVectorForEach(objects, trainerEnvironment.randomNumbersGenerator());
            Map<S, Vector> subjMatrix = generateRandomVectorForEach(subjects, trainerEnvironment.randomNumbersGenerator());

            // SGD steps.
            // TODO: GG-22916 Add convergence check into recommendation system SGD
            for (int i = 0; i < maxIterations; i++) {
                int seed = i;

                // Calculate gradient on reach partition and aggregate results.
                MatrixFactorizationGradient<O, S> grad = dataset.compute(
                    (data, env) -> data.calculateGradient(
                        objMatrix,
                        subjMatrix,
                        batchSize,
                        seed ^ env.partition(),
                        regParam,
                        learningRate
                    ),
                    RecommendationTrainer::sum
                );

                // Apply aggregated gradient.
                grad.applyGradient(objMatrix, subjMatrix);
            }

            return new RecommendationModel<>(objMatrix, subjMatrix);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }