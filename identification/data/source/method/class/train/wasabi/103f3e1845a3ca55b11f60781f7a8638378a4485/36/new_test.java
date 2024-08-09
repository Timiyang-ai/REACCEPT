    @Test
    public void getBatchAssignmentForPage() throws Exception {
        List<Assignment> assignmentsFromPage = new ArrayList<>();

        when(assignments.doPageAssignments(applicationName, pageName, userID, context,
                createAssignment, ignoreSamplingPercent, headers, null,forceProfileCheck)).thenReturn(assignmentsFromPage);

        assertNotNull(resource.getBatchAssignmentForPage(applicationName, pageName, userID, createAssignment,
                ignoreSamplingPercent, context, headers));
    }