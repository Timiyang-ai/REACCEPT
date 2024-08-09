@Test
    public void testTrain_C()
    {
        System.out.println("classify");
        
        ClassificationDataSet train = FixedProblems.get2ClassLinear(200, RandomUtil.getRandom());
        
        ALMA2 alma = new ALMA2();
        alma.setEpochs(1);
        
        alma.trainC(train);
        
        ClassificationDataSet test = FixedProblems.get2ClassLinear(200, RandomUtil.getRandom());
        
        for(DataPointPair<Integer> dpp : test.getAsDPPList())
            assertEquals(dpp.getPair().longValue(), alma.classify(dpp.getDataPoint()).mostLikely());
        
    }