public void predict(Dataframe newData) {
        logger.info("predict()");

        //load all trainables on the bundles
        initBundle();

        //set the parallized flag to all algorithms
        bundle.setParallelized(isParallelized());

        //run the pipeline
        AbstractTransformer dataTransformer = (AbstractTransformer) bundle.get("dataTransformer");
        if(dataTransformer != null) {
            dataTransformer.transform(newData);
        }
        AbstractFeatureSelector featureSelector = (AbstractFeatureSelector) bundle.get("featureSelector");
        if(featureSelector != null) {
            featureSelector.transform(newData);
        }
        AbstractModeler modeler = (AbstractModeler) bundle.get("modeler");
        modeler.predict(newData);
        if(dataTransformer != null) {
            dataTransformer.denormalize(newData);
        }
    }