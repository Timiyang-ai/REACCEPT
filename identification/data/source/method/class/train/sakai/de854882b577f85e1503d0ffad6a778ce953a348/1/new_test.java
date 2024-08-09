    @Test
    public void allowAddSubmissionCheckGroups() {
        String context = UUID.randomUUID().toString();
        String contextReference = AssignmentReferenceReckoner.reckoner().context(context).reckon().getReference();
        String siteReference = "/site/" + context;
        Assignment assignment = createNewAssignment(context);
        // permissions
        when(securityService.unlock(AssignmentServiceConstants.SECURE_ADD_ASSIGNMENT, contextReference)).thenReturn(false);
        when(securityService.unlock(AssignmentServiceConstants.SECURE_ADD_ASSIGNMENT_SUBMISSION, contextReference)).thenReturn(false);
        when(securityService.unlock(AssignmentServiceConstants.SECURE_ADD_ASSIGNMENT_SUBMISSION, "/site/" + context)).thenReturn(true);
        when(siteService.siteReference(context)).thenReturn(siteReference);

        // test with no groups
        Assert.assertTrue(assignmentService.allowAddSubmissionCheckGroups(assignment));

        // test with a groups
        assignment.setTypeOfAccess(Assignment.Access.GROUP);
        String groupA = UUID.randomUUID().toString();
        String groupB = UUID.randomUUID().toString();
        String groupRefA = "/site/" + context + "/group/" + groupA;
        String groupRefB = "/site/" + context + "/group/" + groupB;

        // group A is allowed
        when(securityService.unlock(AssignmentServiceConstants.SECURE_ADD_ASSIGNMENT_SUBMISSION, groupRefA)).thenReturn(true);
        assignment.getGroups().add(groupRefA);
        Assert.assertTrue(assignmentService.allowAddSubmissionCheckGroups(assignment));

        // group B is not allowed
        assignment.getGroups().clear();
        assignment.getGroups().add(groupRefB);
        Assert.assertFalse(assignmentService.allowAddSubmissionCheckGroups(assignment));

        // give group B asn.all.groups and should be allowed now
        when(securityService.unlock(AssignmentServiceConstants.SECURE_ALL_GROUPS, siteReference)).thenReturn(true);
        Assert.assertTrue(assignmentService.allowAddSubmissionCheckGroups(assignment));
    }