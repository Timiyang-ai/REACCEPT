@Test
    public void testTrainAndValidate() {
        logger.info("testTrainAndValidate");
        
        Configuration conf = Configuration.getConfiguration();
        
        Dataframe[] data = Datasets.carsNumeric(conf);
        Dataframe trainingData = data[0];
        
        Dataframe validationData = data[1];
        
        
        String storageName = this.getClass().getSimpleName();

        Modeler.TrainingParameters trainingParameters = new Modeler.TrainingParameters();
        
        
        //Model Configuration

        MultinomialNaiveBayes.TrainingParameters modelTrainingParameters = new MultinomialNaiveBayes.TrainingParameters();
        modelTrainingParameters.setMultiProbabilityWeighted(true);
        trainingParameters.setModelerTrainingParameters(modelTrainingParameters);

        //data transformation configuration
        DummyXMinMaxNormalizer.TrainingParameters dtParams = new DummyXMinMaxNormalizer.TrainingParameters();
        trainingParameters.setDataTransformerTrainingParameters(dtParams);
        
        //feature selection configuration
        trainingParameters.setFeatureSelectorTrainingParameters(null);

        Modeler instance = MLBuilder.create(trainingParameters, storageName, conf);
        instance.fit(trainingData);
        instance.save();

        instance.close();

        instance = MLBuilder.load(Modeler.class, storageName, conf);

        instance.predict(trainingData);

        ClassificationMetrics vm = new ClassificationMetrics(trainingData);

        double expResult2 = 0.8;
        assertEquals(expResult2, vm.getMacroF1(), Constants.DOUBLE_ACCURACY_HIGH);

        instance.close();
        //instance = null;


        instance = MLBuilder.load(Modeler.class, storageName, conf);
        
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