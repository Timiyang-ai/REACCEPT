public ClassifierValidator.ValidationMetrics kFoldCrossValidation(Dataframe trainingData, TP trainingParameters, int k) {
        logger.info("kFoldCrossValidation()");

        return new TemporaryKFold<>(new ClassifierValidator()).kFoldCrossValidation(trainingData, k, dbName, knowledgeBase.getConf(), this.getClass(), trainingParameters);
    }