    @Test
    public void getAssignmentCounts() throws Exception {
        when(analytics.getAssignmentCounts(experimentID, context)).thenReturn(assignmentCounts);
        whenHttpHeader(assignmentCounts);

        analyticsResource.getAssignmentCounts(experimentID, context, "foo");

        verifyAuthorizedExperimentGetterWithExperimentID();
        verify(analytics).getAssignmentCounts(experimentID, context);
        verifyHttpHeader(assignmentCounts);
    }