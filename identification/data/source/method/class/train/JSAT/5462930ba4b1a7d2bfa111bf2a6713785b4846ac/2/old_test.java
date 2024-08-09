@Test
    public void testTrainC_ClassificationDataSet_ExecutorService()
    {
        System.out.println("trainC");
        ClassificationDataSet trainSet = FixedProblems.getInnerOuterCircle(150, new Random(2));
        ClassificationDataSet testSet = FixedProblems.getInnerOuterCircle(50, new Random(3));

        for (SupportVectorLearner.CacheMode cacheMode : SupportVectorLearner.CacheMode.values())
        {
            LSSVM classifier = new LSSVM(new RBFKernel(0.5), cacheMode);
            classifier.setCacheMode(cacheMode);
            classifier.setC(1);
            classifier.train(trainSet, true);

            for (int i = 0; i < testSet.getSampleSize(); i++)
                assertEquals(testSet.getDataPointCategory(i), classifier.classify(testSet.getDataPoint(i)).mostLikely());
        }
    }