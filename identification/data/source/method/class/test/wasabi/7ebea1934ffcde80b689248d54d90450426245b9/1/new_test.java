    @Ignore /* TO BE IMPLEMENTED */
    public void getExperimentStatisticsTest() {
        AnalyticsImpl analyticsImpl = spy(new AnalyticsImpl(experiments, assignmentsRepository, transactionFactory,
                analyticsRepository, analysisTools, experimentRepository));
        Assert.fail();
    }