    @Test
    public void getPriorities() throws Exception {
        when(authorization.getUser("foo")).thenReturn(username);
        when(priorities.getPriorities(applicationName, true)).thenReturn(prioritizedExperimentList);
        whenHttpHeader(prioritizedExperimentList);

        applicationsResource.getPriorities(applicationName, "foo");

        verify(authorization).getUser("foo");
        verify(authorization).checkUserPermissions(username, applicationName, READ);
        verifyHttpHeader(prioritizedExperimentList);
    }