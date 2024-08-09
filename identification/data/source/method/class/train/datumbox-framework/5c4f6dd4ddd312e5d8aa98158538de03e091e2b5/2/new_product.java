public ClassificationMetrics kFoldCrossValidation(Dataframe trainingData, TP trainingParameters, int k) {
        logger.info("validate()");

        return new TemporaryKFold<>(ClassificationMetrics.class).validate(trainingData, k, dbName, knowledgeBase.getConf(), this.getClass(), trainingParameters);
    }