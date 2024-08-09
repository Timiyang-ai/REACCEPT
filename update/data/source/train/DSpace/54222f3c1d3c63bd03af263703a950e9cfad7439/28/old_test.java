@Test
    public void testSetWorkflowGroup() throws SQLException, AuthorizeException
    {
        context.turnOffAuthorisationSystem(); //must be an Admin to create a Group
        int step = 1;
        Group g = Group.create(context);
        context.commit();
        context.restoreAuthSystemState();
        c.setWorkflowGroup(step, g);
        assertThat("testSetWorkflowGroup 0",c.getWorkflowGroup(step), notNullValue());
        assertThat("testSetWorkflowGroup 1",c.getWorkflowGroup(step), equalTo(g));
    }