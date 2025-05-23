@Test
    public void testTrainAndValidate() {
        logger.info("testTrainAndValidate");
        
        Configuration configuration = Configuration.getConfiguration();

        Dataframe[] data = Datasets.heartDiseaseClusters(configuration);

        Dataframe trainingData = data[0];
        Dataframe validationData = data[0].copy();
        Dataframe testData = data[1];
        
        String storageName = this.getClass().getSimpleName();

        Modeler.TrainingParameters trainingParameters = new Modeler.TrainingParameters();
        

        //numerical scaling configuration
        MinMaxScaler.TrainingParameters nsParams = new MinMaxScaler.TrainingParameters();
        trainingParameters.setNumericalScalerTrainingParameters(nsParams);

        //categorical encoding configuration
        OneHotEncoder.TrainingParameters ceParams = new OneHotEncoder.TrainingParameters();
        trainingParameters.setCategoricalEncoderTrainingParameters(ceParams);
        
        //feature selection configuration

        PCA.TrainingParameters pcaParams = new PCA.TrainingParameters();
        pcaParams.setVariancePercentageThreshold(0.99999995);
        trainingParameters.setFeatureSelectorTrainingParametersList(Arrays.asList(new ChisquareSelect.TrainingParameters(), pcaParams));

        //model Configuration
        SoftMaxRegression.TrainingParameters modelTrainingParameters = new SoftMaxRegression.TrainingParameters();
        modelTrainingParameters.setL1(0.0001);
        modelTrainingParameters.setL2(0.0001);
        modelTrainingParameters.setTotalIterations(100);
        trainingParameters.setModelerTrainingParameters(modelTrainingParameters);

        Modeler instance = MLBuilder.create(trainingParameters, configuration);
        instance.fit(trainingData);
        instance.save(storageName);

        instance.close();
        trainingData.close();

        instance = MLBuilder.load(Modeler.class, storageName, configuration);

        instance.predict(validationData);

        ClassificationMetrics vm = new ClassificationMetrics(validationData);

        double expResult2 = 0.8428731762065095;
        assertEquals(expResult2, vm.getMacroF1(), Constants.DOUBLE_ACCURACY_HIGH);

        validationData.close();
        instance.close();


        instance = MLBuilder.load(Modeler.class, storageName, configuration);
        
        instance.predict(testData);
        
        
        
        Map<Integer, Object> expResult = new HashMap<>();
        Map<Integer, Object> result = new HashMap<>();
        for(Map.Entry<Integer, Record> e : testData.entries()) {
            Integer rId = e.getKey();
            Record r = e.getValue();
            expResult.put(rId, r.getY());
            result.put(rId, r.getYPredicted());
        }
        assertEquals(expResult, result);
        
        instance.delete();

        testData.close();
    }