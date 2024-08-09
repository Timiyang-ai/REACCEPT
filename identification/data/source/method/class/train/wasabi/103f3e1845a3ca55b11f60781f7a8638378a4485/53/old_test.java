    @Test
    public void getExperimentStatisticsDailies() throws Exception {
        when(analytics.getExperimentStatisticsDailies(eq(experimentID), any(Parameters.class)))
                .thenReturn(experimentCumulativeStatistics);
        whenHttpHeader(experimentCumulativeStatistics);

        analyticsResource.getExperimentStatisticsDailies(experimentID, context, "foo");

        verifyAuthorizedExperimentGetterWithExperimentID();
        verifyHttpHeader(experimentCumulativeStatistics);
    }