    @Test
    public void getExperimentCounts() throws Exception {
        when(analytics.getExperimentRollup(eq(experimentID), any(Parameters.class)))
                .thenReturn(experimentCounts);
        whenHttpHeader(experimentCounts);

        analyticsResource.getExperimentCounts(experimentID, context, "foo");

        verifyAuthorizedExperimentGetter();
        verify(analytics).getExperimentRollup(eq(experimentID), any(Parameters.class));
        verifyHttpHeader(experimentCounts);
    }