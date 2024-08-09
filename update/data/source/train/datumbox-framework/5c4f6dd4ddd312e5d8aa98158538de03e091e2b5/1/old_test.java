@Test
    public void testValidate() {
        logger.info("validate"); 
        
        Configuration conf = Configuration.getConfiguration();
        
        Dataframe[] data = Datasets.gaussianClusters(conf);
        
        Dataframe trainingData = data[0];
        Dataframe validationData = data[1];

        
        String dbName = this.getClass().getSimpleName();
        GaussianDPMM instance = new GaussianDPMM(dbName, conf);
        
        GaussianDPMM.TrainingParameters param = new GaussianDPMM.TrainingParameters();
        param.setAlpha(0.01);
        param.setMaxIterations(100);
        param.setInitializationMethod(GaussianDPMM.TrainingParameters.Initialization.ONE_CLUSTER_PER_RECORD);
        param.setKappa0(0);
        param.setNu0(1);
        param.setMu0(new double[]{0.0, 0.0});
        param.setPsi0(new double[][]{{1.0,0.0},{0.0,1.0}});
        
        instance.fit(trainingData, param);
        
        instance.close();
        //instance = null;
        instance = new GaussianDPMM(dbName, conf);

        ClustererValidator.ValidationMetrics vm = instance.validate(validationData);

        double expResult = 1.0;
        double result = vm.getPurity();
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
        
        instance.delete();
        
        trainingData.delete();
        validationData.delete();
    }