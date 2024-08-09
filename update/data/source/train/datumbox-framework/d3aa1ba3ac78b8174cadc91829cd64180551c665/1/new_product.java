@Override
    public AbstractRegressor.ValidationMetrics kFoldCrossValidation(Dataframe trainingData, TrainingParameters trainingParameters, int k) {
        if(mlregressor == null) {
            throw new RuntimeException("You need to train a Regressor before running k-fold cross validation.");
        }
        else {
            return (ValidationMetrics) mlregressor.kFoldCrossValidation(trainingData, trainingParameters, k);
        }
    }