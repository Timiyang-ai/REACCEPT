@Test
    public void testSetWorkflowGroup() throws SQLException, AuthorizeException
    {
        context.turnOffAuthorisationSystem(); //must be an Admin to create a Group
        int step = 1;
        Group g = groupService.create(context);
        context.restoreAuthSystemState();
        collection.setWorkflowGroup(context, step, g);
        assertThat("testSetWorkflowGroup 0",collectionService.getWorkflowGroup(collection, step), notNullValue());
        assertThat("testSetWorkflowGroup 1",collectionService.getWorkflowGroup(collection, step), equalTo(g));
    }