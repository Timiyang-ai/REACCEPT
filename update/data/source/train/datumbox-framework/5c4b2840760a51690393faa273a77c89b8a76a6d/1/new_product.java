@SuppressWarnings("unchecked")
    public VM kFoldCrossValidation(Dataset trainingData, int k) {
        if(GeneralConfiguration.DEBUG) {
            System.out.println("kFoldCrossValidation()");
        }
        
        return modelValidator.kFoldCrossValidation(trainingData, k, dbName, this.getClass(), knowledgeBase.getTrainingParameters());
    }