@Test
public void testTrainAndValidate() {
    logger.info("testTrainAndValidate");
    
    Configuration configuration = Configuration.getConfiguration();
    
    Dataframe[] data = Datasets.carsNumeric(configuration);
    Dataframe trainingData = data[0];
    
    Dataframe validationData = data[1];
    
    
    String storageName = this.getClass().getSimpleName();

    Modeler.TrainingParameters trainingParameters = new Modeler.TrainingParameters();
    
    //Model Configuration
    MultinomialNaiveBayes.TrainingParameters modelTrainingParameters = new MultinomialNaiveBayes.TrainingParameters();
    modelTrainingParameters.setMultiProbabilityWeighted(true);
    trainingParameters.setModelerTrainingParameters(modelTrainingParameters);

    //numerical scaling configuration
    trainingParameters.setNumericalScalerTrainingParameters(null);

    //categorical encoding configuration
    trainingParameters.setCategoricalEncoderTrainingParameters(null);
    
    //feature selection configuration
    trainingParameters.setFeatureSelectorTrainingParameters(null);

    Modeler instance = MLBuilder.create(trainingParameters, configuration);
    instance.fit(trainingData);
    instance.save(storageName);

    instance.close();

    instance = MLBuilder.load(Modeler.class, storageName, configuration);

    instance.predict(trainingData);

    ClassificationMetrics vm = new ClassificationMetrics(trainingData);

    double expResult2 = 0.8;
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