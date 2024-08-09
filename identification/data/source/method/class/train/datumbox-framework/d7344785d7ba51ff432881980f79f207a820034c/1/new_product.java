public void _predict(Dataframe newData) {
        //load all trainables on the bundles
        initBundle();

        //set the parallized flag to all algorithms
        bundle.setParallelized(isParallelized());

        //run the pipeline
        AbstractNumericalScaler numericalScaler = (AbstractNumericalScaler) bundle.get(NS_KEY);
        if(numericalScaler != null) {
            numericalScaler.transform(newData);
        }
        AbstractCategoricalEncoder categoricalEncoder = (AbstractCategoricalEncoder) bundle.get(CE_KEY);
        if(categoricalEncoder != null) {
            categoricalEncoder.transform(newData);
        }
        AbstractFeatureSelector featureSelector = (AbstractFeatureSelector) bundle.get(FS_KEY);
        if(featureSelector != null) {
            featureSelector.transform(newData);
        }
        AbstractModeler modeler = (AbstractModeler) bundle.get(ML_KEY);
        modeler.predict(newData);
    }