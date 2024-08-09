    @Test
    public void getExperimentStatisticsParameters() throws Exception {
        when(analytics.getExperimentStatistics(experimentID, parameters)).thenReturn(experimentStatistics);
        whenHttpHeader(experimentStatistics);

        analyticsResource.getExperimentStatisticsParameters(experimentID, parameters, "foo");

        verifyAuthorizedExperimentGetterWithExperimentID();
        verify(parameters).parse();
        verifyHttpHeader(experimentStatistics);
    }