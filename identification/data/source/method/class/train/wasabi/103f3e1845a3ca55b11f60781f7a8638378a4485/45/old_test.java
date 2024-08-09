    @Test
    public void getExperimentCountsDailies() throws Exception {
        when(analytics.getExperimentRollupDailies(eq(experimentID), any(Parameters.class)))
                .thenReturn(experimentCumulativeCounts);
        whenHttpHeader(experimentCumulativeCounts);

        analyticsResource.getExperimentCountsDailies(experimentID, context, "foo");

        verifyAuthorizedExperimentGetterWithExperimentID();
        verify(analytics).getExperimentRollupDailies(eq(experimentID), any(Parameters.class));
        verifyHttpHeader(experimentCumulativeCounts);
    }