public <K,V> List<IgniteModel<Vector, Double>> update(GDBTrainer.GDBModel mdlToUpdate,
        DatasetBuilder<K, V> datasetBuilder, IgniteBiFunction<K, V, Vector> featureExtractor,
        IgniteBiFunction<K, V, Double> lbExtractor) {
        if (trainerEnvironment == null)
            throw new IllegalStateException("Learning environment builder is not set.");

        List<IgniteModel<Vector, Double>> models = initLearningState(mdlToUpdate);

        ConvergenceChecker<K, V> convCheck = checkConvergenceStgyFactory.create(sampleSize,
            externalLbToInternalMapping, loss, datasetBuilder, featureExtractor, lbExtractor);

        DatasetTrainer<? extends IgniteModel<Vector, Double>, Double> trainer = baseMdlTrainerBuilder.get();
        for (int i = 0; i < cntOfIterations; i++) {
            double[] weights = Arrays.copyOf(compositionWeights, models.size());

            WeightedPredictionsAggregator aggregator = new WeightedPredictionsAggregator(weights, meanLbVal);
            ModelsComposition currComposition = new ModelsComposition(models, aggregator);
            if (convCheck.isConverged(envBuilder, datasetBuilder, currComposition))
                break;

            IgniteBiFunction<K, V, Double> lbExtractorWrap = (k, v) -> {
                Double realAnswer = externalLbToInternalMapping.apply(lbExtractor.apply(k, v));
                Double mdlAnswer = currComposition.predict(featureExtractor.apply(k, v));
                return -loss.gradient(sampleSize, realAnswer, mdlAnswer);
            };

            long startTs = System.currentTimeMillis();
            models.add(trainer.fit(datasetBuilder, featureExtractor, lbExtractorWrap));
            double learningTime = (double)(System.currentTimeMillis() - startTs) / 1000.0;
            trainerEnvironment.logger(getClass()).log(MLLogger.VerboseLevel.LOW, "One model training time was %.2fs", learningTime);
        }

        return models;
    }