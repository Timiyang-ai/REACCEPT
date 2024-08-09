public void predict(Dataframe newData) {
        logger.info("predict()");

        Modeler.TrainingParameters trainingParameters = knowledgeBase.getTrainingParameters();
        Configuration conf = knowledgeBase.getConf();

        AbstractTrainer.AbstractTrainingParameters dtParams = trainingParameters.getDataTransformerTrainingParameters();
        boolean transformData = dtParams!=null;
        if(transformData) {
            if(dataTransformer==null) {
                dataTransformer = MLBuilder.load(dtParams.getTClass(), dbName, conf);
            }
            setParallelized(dataTransformer);
            dataTransformer.transform(newData);
        }

        AbstractTrainer.AbstractTrainingParameters fsParams = trainingParameters.getFeatureSelectorTrainingParameters();
        boolean selectFeatures = fsParams!=null;
        if(selectFeatures) {
            if(featureSelector==null) {
                featureSelector = MLBuilder.load(fsParams.getTClass(), dbName, conf);
            }
            setParallelized(featureSelector);
            featureSelector.transform(newData);
        }

        if(modeler==null) {
            modeler = MLBuilder.load(trainingParameters.getModelerTrainingParameters().getTClass(), dbName, conf);
        }
        setParallelized(modeler);
        modeler.predict(newData);

        if(transformData) {
            dataTransformer.denormalize(newData);
        }
    }