@Test
    public void testTrain_RegressionDataSet_ExecutorService()
    {
        System.out.println("train");
        
        RegressionDataSet trainSet =  FixedProblems.getSimpleRegression1(2000, RandomUtil.getRandom());
        RegressionDataSet testSet =  FixedProblems.getSimpleRegression1(200, RandomUtil.getRandom());
        
        ExecutorService threadPool = Executors.newFixedThreadPool(SystemInfo.LogicalCores);
        
        for(RBFNet.Phase1Learner p1l : RBFNet.Phase1Learner.values())
            for(RBFNet.Phase2Learner p2l :  EnumSet.complementOf(EnumSet.of(RBFNet.Phase2Learner.CLOSEST_OPPOSITE_CENTROID)))
            {
                RBFNet net = new RBFNet(25);
                net.setAlpha(1);//CLOSEST_OPPOSITE_CENTROID needs a smaller value, shoudld be fine for others on this data set 
                net.setPhase1Learner(p1l);
                net.setPhase2Learner(p2l);
                net = net.clone();
                net.train(trainSet, threadPool);
                net = net.clone();
                
                double errors = 0;
                for (int i = 0; i < testSet.getSampleSize(); i++)
                    errors += Math.pow(testSet.getTargetValue(i) - net.regress(testSet.getDataPoint(i)), 2);
                assertTrue(errors/testSet.getSampleSize() < 1);
            }
        
        threadPool.shutdown();
    }