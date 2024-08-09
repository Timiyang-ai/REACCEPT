@Test
    public void testPredict() {
        logger.info("testPredict");
        
        Configuration conf = Configuration.getConfiguration();
        
        
        Dataframe[] data = Datasets.carsNumeric(conf);
        
        Dataframe trainingData = data[0];
        Dataframe validationData = data[1];
        
        
        String dbName = this.getClass().getSimpleName();
        BernoulliNaiveBayes instance = MLBuilder.create(new BernoulliNaiveBayes.TrainingParameters(), dbName, conf);
        
        instance.fit(trainingData);
        instance.save();
        
        instance.close();
        //instance = null;
        instance = MLBuilder.load(BernoulliNaiveBayes.class, dbName, conf);
        
        
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