@Test
    public void testValidate() {
        TestUtils.log(this.getClass(), "validate"); 
        RandomValue.randomGenerator = new Random(42); 
        
        Dataset trainingData = generateDataset();
        Dataset validationData = new Dataset();
        validationData.add(Record.newDataVector(new Object[] {51,"M","3",100,222,"no","0",143,"yes", 1.2,2,0,"3"}, "healthy"));
        validationData.add(Record.newDataVector(new Object[] {67,"M","4",120,229,"no","2",129,"yes", 2.6,2,2,"7"}, "problem"));
        
        
        
        String dbName = "JUnitClusterer";
        

        DummyXYMinMaxNormalizer df = new DummyXYMinMaxNormalizer(dbName, TestConfiguration.getDBConfig());
        df.fit_transform(trainingData, new DummyXYMinMaxNormalizer.TrainingParameters());
        
        df.transform(validationData);
        
        
        Kmeans instance = new Kmeans(dbName, TestConfiguration.getDBConfig());
        
        Kmeans.TrainingParameters param = new Kmeans.TrainingParameters();
        param.setK(2);
        param.setMaxIterations(200);
        param.setInitMethod(Kmeans.TrainingParameters.Initialization.FORGY);
        param.setDistanceMethod(Kmeans.TrainingParameters.Distance.EUCLIDIAN);
        param.setWeighted(false);
        param.setCategoricalGamaMultiplier(1.0);
        param.setSubsetFurthestFirstcValue(2.0);
        
        instance.fit(trainingData, param);
        
        
        instance = null;
        instance = new Kmeans(dbName, TestConfiguration.getDBConfig());
        
        instance.validate(validationData);
        
        df.denormalize(trainingData);
        df.denormalize(validationData);
        df.erase();
        
        Map<Integer, Object> expResult = new HashMap<>();
        Map<Integer, Object> result = new HashMap<>();
        
        Map<Integer, Kmeans.Cluster> clusters = instance.getClusters();
        for(Record r : validationData) {
            expResult.put(r.getId(), r.getY());
            Integer clusterId = (Integer) r.getYPredicted();
            Object label = clusters.get(clusterId).getLabelY();
            if(label==null) {
                label = clusterId;
            }
            result.put(r.getId(), label);
        }
        assertEquals(expResult, result);
        
        instance.erase();
    }