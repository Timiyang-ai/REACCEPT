@SuppressWarnings("unchecked")
    public VM kFoldCrossValidation(Dataset trainingData, TP trainingParameters, int k) {
        if(GeneralConfiguration.DEBUG) {
            System.out.println("kFoldCrossValidation()");
        }
        initializeTrainingConfiguration(trainingParameters);
        
        return modelValidator.kFoldCrossValidation(trainingData, k, dbName, knowledgeBase.getDbConf(), this.getClass(), knowledgeBase.getTrainingParameters());
    }