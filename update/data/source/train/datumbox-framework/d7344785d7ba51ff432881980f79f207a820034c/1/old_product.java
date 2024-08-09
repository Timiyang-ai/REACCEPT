public void predict(Dataframe newData) {
        logger.info("predict()");

        //load all trainables on the bundles
        initBundle();

        //set the parallized flag to all algorithms
        bundle.setParallelized(isParallelized());

        //run the pipeline
        AbstractTransformer dataTransformer = (AbstractTransformer) bundle.get(DT_KEY);
        if(dataTransformer != null) {
            dataTransformer.transform(newData);
        }
        AbstractFeatureSelector featureSelector = (AbstractFeatureSelector) bundle.get(FS_KEY);
        if(featureSelector != null) {
            featureSelector.transform(newData);
        }
        AbstractModeler modeler = (AbstractModeler) bundle.get(ML_KEY);
        modeler.predict(newData);
        if(dataTransformer != null) {
            dataTransformer.denormalize(newData);
        }
    }