@Test
    public void testValidate() {
        logger.info("validate");
        
        DatabaseConfiguration dbConf = TestUtils.getDBConfig();
        
        Dataframe[] data = Datasets.recommenderSystemFood(dbConf);
        
        Dataframe trainingData = data[0];
        Dataframe validationData = data[1];
        
        
        String dbName = this.getClass().getSimpleName();
        CollaborativeFiltering instance = new CollaborativeFiltering(dbName, dbConf);
        
        CollaborativeFiltering.TrainingParameters param = new CollaborativeFiltering.TrainingParameters();
        param.setSimilarityMethod(CollaborativeFiltering.TrainingParameters.SimilarityMeasure.PEARSONS_CORRELATION);
        
        instance.fit(trainingData, param);
        
        instance.close();
        //instance = null;
        instance = new CollaborativeFiltering(dbName, dbConf);
        
        CollaborativeFiltering.ValidationMetrics vm = instance.validate(validationData);
        
        Map<Object, Double> expResult = new HashMap<>();
        expResult.put("pitta", 4.686394033077408);
        expResult.put("burger", 4.68408210680137);
        expResult.put("pizza", 4.6194430718558745);
        expResult.put("chocolate", 4.580630241051733);
        expResult.put("potato", 4.291658734729706);
        expResult.put("beer", 4.264285969929414);
        expResult.put("sparklewatter", 2.8034325749458997);
        expResult.put("salad", 1.496493323119103);
        expResult.put("risecookie", 1.372309723394662);
        expResult.put("tea", 1.3577402217087802);
        expResult.put("rise", 1.2243050068650592);
        
        AssociativeArray result = validationData.iterator().next().getYPredictedProbabilities();
        for(Map.Entry<Object, Object> entry : result.entrySet()) {
            assertEquals(expResult.get(entry.getKey()), TypeInference.toDouble(entry.getValue()), TestConfiguration.DOUBLE_ACCURACY_HIGH);
        }
        
        assertEquals(vm.getRMSE(), 0.7184568473420477, TestConfiguration.DOUBLE_ACCURACY_HIGH);
        
        instance.delete();
        
        trainingData.delete();
        validationData.delete();
    }