@Test
    public void testKFoldCrossValidation() {
        System.out.println("kFoldCrossValidation");
        RandomValue.randomGenerator = new Random(42); 
        int k = 5;
        
        Dataset trainingData = generateDataset();
        
        MemoryConfiguration memoryConfiguration = new MemoryConfiguration();
        
        
        String dbName = "JUnitRegressor";


        
        
        
        GaussianDPMM instance = new GaussianDPMM(dbName);
        
        GaussianDPMM.TrainingParameters param = instance.getEmptyTrainingParametersObject();
        param.setAlpha(0.01);
        param.setMaxIterations(100);
        param.setInitializationMethod(BaseDPMM.TrainingParameters.Initialization.ONE_CLUSTER_PER_RECORD);
        param.setKappa0(0);
        param.setNu0(1);
        param.setMu0(new double[]{0.0, 0.0});
        param.setPsi0(new double[][]{{1.0,0.0},{0.0,1.0}});
        instance.initializeTrainingConfiguration(memoryConfiguration, param);
        GaussianDPMM.ValidationMetrics vm = instance.kFoldCrossValidation(trainingData, k);

        
        double expResult = 1.0;
        double result = vm.getPurity();
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_MEDIUM);
        instance.erase(true);
    }