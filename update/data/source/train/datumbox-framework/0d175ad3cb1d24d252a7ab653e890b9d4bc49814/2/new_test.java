@Test
    public void testTrainAndValidate() {
        logger.info("testTrainAndValidate");
        
        Configuration conf = Configuration.getConfiguration();
        
        Dataframe[] data = Datasets.carsNumeric(conf);
        Dataframe trainingData = data[0];
        
        Dataframe validationData = data[1];
        
        
        String dbName = this.getClass().getSimpleName();
        
        Modeler instance = new Modeler(dbName, conf);
        Modeler.TrainingParameters trainingParameters = new Modeler.TrainingParameters();
        
        
        //Model Configuration
        
        trainingParameters.setModelerClass(MultinomialNaiveBayes.class);
        MultinomialNaiveBayes.TrainingParameters modelTrainingParameters = new MultinomialNaiveBayes.TrainingParameters();
        modelTrainingParameters.setMultiProbabilityWeighted(true);
        trainingParameters.setModelerTrainingParameters(modelTrainingParameters);

        //data transfomation configuration
        trainingParameters.setDataTransformerClass(DummyXMinMaxNormalizer.class);
        DummyXMinMaxNormalizer.TrainingParameters dtParams = new DummyXMinMaxNormalizer.TrainingParameters();
        trainingParameters.setDataTransformerTrainingParameters(dtParams);
        
        //feature selection configuration
        trainingParameters.setFeatureSelectorClass(null);
        trainingParameters.setFeatureSelectorTrainingParameters(null);
        
        instance.fit(trainingData, trainingParameters);
        
        /*
        //TODO: restore this
        ClassifierValidator.ValidationMetrics vm = instance.validate(trainingData);

        double expResult2 = 0.8;
        Assert.assertEquals(expResult2, vm.getMacroF1(), Constants.DOUBLE_ACCURACY_HIGH);
        */

        instance.close();
        //instance = null;
        
        
        logger.info("validate");
        
        
        instance = new Modeler(dbName, conf);
        
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
        
        trainingData.delete();
        validationData.delete();
    }