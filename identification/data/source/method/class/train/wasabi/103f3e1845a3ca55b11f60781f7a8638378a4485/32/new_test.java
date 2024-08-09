    @Test
    public void postBatchAssignmentForPage() throws Exception {
        List<Assignment> assignmentsFromPage = new ArrayList<>();

        when(assignments.doPageAssignments(applicationName, pageName, userID, context,
                createAssignment, ignoreSamplingPercent, headers, segmentationProfile,forceProfileCheck)).thenReturn(assignmentsFromPage);

        assertNotNull(resource.postBatchAssignmentForPage(applicationName, pageName, userID, createAssignment,
                ignoreSamplingPercent, context, segmentationProfile, forceProfileCheck, headers));
    }