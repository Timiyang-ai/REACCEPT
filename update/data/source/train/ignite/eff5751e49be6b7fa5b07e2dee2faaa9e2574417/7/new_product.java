public <K,V> List<Model<Vector, Double>> update(GDBTrainer.GDBModel mdlToUpdate,
        DatasetBuilder<K, V> datasetBuilder, IgniteBiFunction<K, V, Vector> featureExtractor,
        IgniteBiFunction<K, V, Double> lbExtractor) {

        List<Model<Vector, Double>> models = initLearningState(mdlToUpdate);

        ConvergenceChecker<K, V> convCheck = checkConvergenceStgyFactory.create(sampleSize,
            externalLbToInternalMapping, loss, datasetBuilder, featureExtractor, lbExtractor);

        DatasetTrainer<? extends Model<Vector, Double>, Double> trainer = baseMdlTrainerBuilder.get();
        for (int i = 0; i < cntOfIterations; i++) {
            double[] weights = Arrays.copyOf(compositionWeights, models.size());

            WeightedPredictionsAggregator aggregator = new WeightedPredictionsAggregator(weights, meanLbVal);
            ModelsComposition currComposition = new ModelsComposition(models, aggregator);
            if (convCheck.isConverged(datasetBuilder, currComposition))
                break;

            IgniteBiFunction<K, V, Double> lbExtractorWrap = (k, v) -> {
                Double realAnswer = externalLbToInternalMapping.apply(lbExtractor.apply(k, v));
                Double mdlAnswer = currComposition.apply(featureExtractor.apply(k, v));
                return -loss.gradient(sampleSize, realAnswer, mdlAnswer);
            };

            long startTs = System.currentTimeMillis();
            models.add(trainer.fit(datasetBuilder, featureExtractor, lbExtractorWrap));
            double learningTime = (double)(System.currentTimeMillis() - startTs) / 1000.0;
            environment.logger(getClass()).log(MLLogger.VerboseLevel.LOW, "One model training time was %.2fs", learningTime);
        }

        return models;
    }