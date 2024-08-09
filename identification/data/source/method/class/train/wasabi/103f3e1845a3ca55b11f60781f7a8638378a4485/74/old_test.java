    @Test
    public void getExperimentStatistics() throws Exception {
        when(analytics.getExperimentStatistics(eq(experimentID), any(Parameters.class)))
                .thenReturn(experimentStatistics);
        whenHttpHeader(experimentStatistics);

        analyticsResource.getExperimentStatistics(experimentID, context, "foo");

        verifyAuthorizedExperimentGetterWithExperimentID();
        verifyHttpHeader(experimentStatistics);
    }