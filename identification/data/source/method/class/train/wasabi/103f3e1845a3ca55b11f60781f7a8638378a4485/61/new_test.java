    @Test
    public void postAssignment() throws Exception {
        when(assignments.doSingleAssignment(userID, applicationName, experimentLabel,
                context, createAssignment, ignoreSamplingPercent, segmentationProfile, headers,forceProfileCheck)).thenReturn(assignment);
        when(assignment.getStatus()).thenReturn(Status.EXPERIMENT_PAUSED);
        when(assignment.getBucketLabel()).thenReturn(label);
        when(assignment.getContext()).thenReturn(context);

        assertNotNull(resource.postAssignment(applicationName, experimentLabel, userID, createAssignment, ignoreSamplingPercent, context, segmentationProfile, forceProfileCheck, headers));
    }