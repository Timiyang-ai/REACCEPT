public VM kFoldCrossValidation(Dataframe trainingData, TP trainingParameters, int k) {
        logger.info("kFoldCrossValidation()");
        
        return modelValidator.kFoldCrossValidation(trainingData, k, dbName, kb().getDbConf(), this.getClass(), trainingParameters);
    }