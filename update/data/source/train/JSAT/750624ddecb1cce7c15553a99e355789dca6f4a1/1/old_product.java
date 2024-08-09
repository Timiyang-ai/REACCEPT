private void train(ClassificationDataSet train, ExecutorService threadPool)
    {
        if(threadPool == null || threadPool instanceof FakeExecutor)
            base.trainC(train);
        else
            base.trainC(train, threadPool);
    }