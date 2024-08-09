    @Test
    public void getAssignment() {
        when(assignments.doSingleAssignment(userID, applicationName, experimentLabel,
                context, createAssignment, ignoreSamplingPercent, null,
                headers,forceProfileCheck)).thenReturn(assignment);
        when(assignment.getStatus()).thenReturn(Status.NEW_ASSIGNMENT);
        when(assignment.getBucketLabel()).thenReturn(label);
        when(assignment.getContext()).thenReturn(context);

        assertNotNull(resource.getAssignment(applicationName, experimentLabel, userID, context, createAssignment, ignoreSamplingPercent, headers));
    }