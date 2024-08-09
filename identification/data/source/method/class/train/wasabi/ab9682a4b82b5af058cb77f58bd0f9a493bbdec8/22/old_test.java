    @Test
    public void postPages() throws Exception {
        ExperimentPageList experimentPageList = new ExperimentPageList();

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.postPages(experiment.getID(), experimentPageList, AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.CREATE);
        try {
            experimentsResource.postPages(experiment.getID(), experimentPageList, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.postPages(experiment.getID(), experimentPageList, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        // simply pass through the method when we get valid input and check if the response is correct
        doReturn(USER).when(authorization).getUser(AUTHHEADER);
        doReturn(experiment).when(experiments).getExperiment(experiment.getID());
        doNothing().when(authorization).checkUserPermissions(USER, experiment.getApplicationName(), Permission.CREATE);
        doNothing().when(pages).postPages(experiment.getID(), experimentPageList, USERINFO);

        Response response = experimentsResource.postPages(experiment.getID(), experimentPageList, AUTHHEADER);
        assertEquals("Response code indicates pages were not set.", response.getStatus(), CREATED.getStatusCode());
    }