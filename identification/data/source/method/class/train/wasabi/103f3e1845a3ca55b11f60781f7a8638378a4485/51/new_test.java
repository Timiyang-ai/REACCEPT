    @Test
    public void getExperimentStatisticsDailiesParameters() throws Exception {
        when(analytics.getExperimentStatisticsDailies(experimentID, parameters))
                .thenReturn(experimentCumulativeStatistics);
        whenHttpHeader(experimentCumulativeStatistics);

        analyticsResource.getExperimentStatisticsDailiesParameters(experimentID, parameters, "foo");

        verifyAuthorizedExperimentGetterWithExperimentID();
        verify(parameters).parse();
        verifyHttpHeader(experimentCumulativeStatistics);
    }