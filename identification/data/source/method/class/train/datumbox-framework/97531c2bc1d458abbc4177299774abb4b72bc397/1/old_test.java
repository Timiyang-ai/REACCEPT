@Test
    public void testValidate() {
        logger.info("validate");
        
        Configuration conf = Configuration.getConfiguration();
        
        
        Dataframe[] data = Datasets.winesOrdinal(conf);
        
        Dataframe trainingData = data[0];
        Dataframe validationData = data[1];
        
        
        String dbName = this.getClass().getSimpleName();
        DummyXMinMaxNormalizer df = new DummyXMinMaxNormalizer(dbName, conf);
        
        df.fit_transform(trainingData, new DummyXMinMaxNormalizer.TrainingParameters());
        df.transform(validationData);
        
        OrdinalRegression instance = new OrdinalRegression(dbName, conf);
        
        OrdinalRegression.TrainingParameters param = new OrdinalRegression.TrainingParameters();
        param.setTotalIterations(100);
        param.setL2(0.001);
        
        instance.fit(trainingData, param);
        
        instance.close();
        df.close();
        //instance = null;
        //df = null;
        
        df = new DummyXMinMaxNormalizer(dbName, conf);
        instance = new OrdinalRegression(dbName, conf);
        
        instance.predict(validationData);

        
        df.denormalize(trainingData);
        df.denormalize(validationData);

        Map<Integer, Object> expResult = new HashMap<>();
        Map<Integer, Object> result = new HashMap<>();
        for(Map.Entry<Integer, Record> e : validationData.entries()) {
            Integer rId = e.getKey();
            Record r = e.getValue();
            expResult.put(rId, r.getY());
            result.put(rId, r.getYPredicted());
        }
        assertEquals(expResult, result);
        
        df.delete();
        instance.delete();
        
        trainingData.delete();
        validationData.delete();
    }