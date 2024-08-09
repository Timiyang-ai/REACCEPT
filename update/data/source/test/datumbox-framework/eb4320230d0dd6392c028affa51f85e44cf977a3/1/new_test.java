@Test
    public void testTrainAndValidate() {
        logger.info("testTrainAndValidate");
        
        Configuration conf = Configuration.getConfiguration();
        
        Dataframe[] data = Datasets.carsNumeric(conf);
        Dataframe trainingData = data[0];
        
        Dataframe validationData = data[1];
        
        
        String dbName = this.getClass().getSimpleName();

        Modeler.TrainingParameters trainingParameters = new Modeler.TrainingParameters();
        
        
        //Model Configuration

        MultinomialNaiveBayes.TrainingParameters modelTrainingParameters = new MultinomialNaiveBayes.TrainingParameters();
        modelTrainingParameters.setMultiProbabilityWeighted(true);
        trainingParameters.setModelerTrainingParameters(modelTrainingParameters);

        //data transfomation configuration
        DummyXMinMaxNormalizer.TrainingParameters dtParams = new DummyXMinMaxNormalizer.TrainingParameters();
        trainingParameters.setDataTransformerTrainingParameters(dtParams);
        
        //feature selection configuration
        trainingParameters.setFeatureSelectorTrainingParameters(null);

        Modeler instance = MLBuilder.create(trainingParameters, dbName, conf);
        instance.fit(trainingData);
        instance.save();

        instance.close();

        instance = MLBuilder.load(Modeler.class, dbName, conf);

        instance.predict(trainingData);

        ClassificationMetrics vm = new ClassificationMetrics(trainingData);

        double expResult2 = 0.8;
        assertEquals(expResult2, vm.getMacroF1(), Constants.DOUBLE_ACCURACY_HIGH);

        trainingData.delete();
        instance.close();
        //instance = null;


        instance = MLBuilder.load(Modeler.class, dbName, conf);
        
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

        validationData.delete();
    }