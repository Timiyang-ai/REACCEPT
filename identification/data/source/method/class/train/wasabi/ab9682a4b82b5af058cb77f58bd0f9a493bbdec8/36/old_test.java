    @Test
    public void getApplications() throws Exception {
        when(authorization.getUser("foo")).thenReturn(username);
        when(experiments.getApplications()).thenReturn(applicationNames);
        whenHttpHeader(applicationNames);

        applicationsResource.getApplications("foo");

        verify(authorization).getUser("foo");
        verify(experiments).getApplications();
        verifyHttpHeader(applicationNames);
    }