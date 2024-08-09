@Test
    public void testTrainAndValidate() {
        logger.info("testTrainAndValidate");
        
        Configuration configuration = Configuration.getConfiguration();
        
        Dataframe[] data = Datasets.heartDiseaseClusters(configuration);

        Dataframe trainingData = data[0];
        Dataframe validationData = data[1];
        
        
        String storageName = this.getClass().getSimpleName();

        Modeler.TrainingParameters trainingParameters = new Modeler.TrainingParameters();
        

        //numerical scaling configuration
        MinMaxScaler.TrainingParameters nsParams = new MinMaxScaler.TrainingParameters();
        trainingParameters.setNumericalScalerTrainingParameters(nsParams);

        //categorical encoding configuration
        OneHotEncoder.TrainingParameters ceParams = new OneHotEncoder.TrainingParameters();
        trainingParameters.setCategoricalEncoderTrainingParameters(ceParams);
        
        //feature selection configuration
        trainingParameters.setFeatureSelectorTrainingParametersList(Arrays.asList());

        //model Configuration
        MultinomialNaiveBayes.TrainingParameters modelTrainingParameters = new MultinomialNaiveBayes.TrainingParameters();
        modelTrainingParameters.setMultiProbabilityWeighted(true);
        trainingParameters.setModelerTrainingParameters(modelTrainingParameters);

        Modeler instance = MLBuilder.create(trainingParameters, configuration);
        instance.fit(trainingData);
        instance.save(storageName);

        instance.close();

        instance = MLBuilder.load(Modeler.class, storageName, configuration);

        instance.predict(trainingData);

        ClassificationMetrics vm = new ClassificationMetrics(trainingData);

        double expResult2 = 0.7867564534231202;
        assertEquals(expResult2, vm.getMacroF1(), Constants.DOUBLE_ACCURACY_HIGH);

        trainingData.close();
        instance.close();


        instance = MLBuilder.load(Modeler.class, storageName, configuration);
        
        instance.predict(validationData);
        
        
        
        Map<Integer, Object> expResult = new HashMap<>();
        Map<Integer, Object> result = new HashMap<>();
        for(Map.Entry<Integer, Record> e : validationData.entries()) {
            Integer rId = e.getKey();
            Record r = e.getValue();
            expResult.put(rId, r.getY());
            result.put(rId, r.getYPredicted());
        }
        assertEquals(expResult, result);
        
        instance.delete();

        validationData.close();
    }