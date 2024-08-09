@Test
    public void testTrain_C()
    {
        System.out.println("train_C");
        ClassificationDataSet train = FixedProblems.get2ClassLinear(200, RandomUtil.getRandom());
        
        AROW arow0 = new AROW(1, true);
        AROW arow1 = new AROW(1, false);
        
        arow0.trainC(train);
        arow1.trainC(train);
        
        
        ClassificationDataSet test = FixedProblems.get2ClassLinear(200, RandomUtil.getRandom());
        
        for(DataPointPair<Integer> dpp : test.getAsDPPList())
            assertEquals(dpp.getPair().longValue(), arow0.classify(dpp.getDataPoint()).mostLikely());
        for(DataPointPair<Integer> dpp : test.getAsDPPList())
            assertEquals(dpp.getPair().longValue(), arow1.classify(dpp.getDataPoint()).mostLikely());
    }