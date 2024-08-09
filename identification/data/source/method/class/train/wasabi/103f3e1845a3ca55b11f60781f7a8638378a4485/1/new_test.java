    @Test
    public void getExperimentCountsDailiesParameters() throws Exception {
        when(analytics.getExperimentRollupDailies(experimentID, parameters))
                .thenReturn(experimentCumulativeCounts);
        whenHttpHeader(experimentCumulativeCounts);

        analyticsResource.getExperimentCountsDailiesParameters(experimentID, parameters, "foo");

        verifyAuthorizedExperimentGetter();
        verify(parameters).parse();
        verifyHttpHeader(experimentCumulativeCounts);
    }