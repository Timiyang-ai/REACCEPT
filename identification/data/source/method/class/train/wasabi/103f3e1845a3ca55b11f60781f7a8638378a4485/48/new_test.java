    @Test
    public void getAssignmentCountsByApp() throws Exception {
        when(authorizedExperimentGetter.getAuthorizedExperimentByName("foo", applicationName,
                experimentLabel))
                .thenReturn(experiment);
        when(experiment.getID()).thenReturn(experimentID);
        when(analytics.getAssignmentCounts(experimentID, context)).thenReturn(assignmentCounts);
        whenHttpHeader(assignmentCounts);

        analyticsResource.getAssignmentCountsByApp(applicationName, experimentLabel, context, "foo");

        verify(authorizedExperimentGetter).getAuthorizedExperimentByName("foo", applicationName,
                experimentLabel);
        verifyHttpHeader(assignmentCounts);
    }