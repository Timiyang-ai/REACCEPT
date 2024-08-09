@Test
    public void testKFoldCrossValidation() {
        logger.info("kFoldCrossValidation");
        
        DatabaseConfiguration dbConf = TestUtils.getDBConfig();
        
        int k = 5;
        
        Dataset[] data = Datasets.carsNumeric(dbConf);
        Dataset trainingData = data[0];
        data[1].erase();
        
        
        String dbName = this.getClass().getSimpleName();
        BernoulliNaiveBayes instance = new BernoulliNaiveBayes(dbName, dbConf);
        
        BernoulliNaiveBayes.TrainingParameters param = new BernoulliNaiveBayes.TrainingParameters();
        
        BernoulliNaiveBayes.ValidationMetrics vm = instance.kFoldCrossValidation(trainingData, param, k);
        
        double expResult = 0.6631318681318682;
        double result = vm.getMacroF1();
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
        instance.erase();
        
        trainingData.erase();
    }