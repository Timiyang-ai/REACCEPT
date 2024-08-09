@SuppressWarnings("unchecked")
    public VM kFoldCrossValidation(Dataset trainingData, TP trainingParameters, int k) {
        logger.info("kFoldCrossValidation()");
        
        initializeTrainingConfiguration(trainingParameters);
        
        return modelValidator.kFoldCrossValidation(trainingData, k, dbName, knowledgeBase.getDbConf(), this.getClass(), knowledgeBase.getTrainingParameters());
    }