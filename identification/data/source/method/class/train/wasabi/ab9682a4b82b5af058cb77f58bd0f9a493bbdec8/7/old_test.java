    @Test
    public void setPriority() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.setPriority(experiment.getID(), 1, AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.CREATE);
        try {
            experimentsResource.setPriority(experiment.getID(), 1, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.setPriority(experiment.getID(), 1, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        // simply pass through the method when we get valid input and check if the response is correct
        doReturn(USER).when(authorization).getUser(AUTHHEADER);
        doReturn(experiment).when(experiments).getExperiment(experiment.getID());
        doNothing().when(authorization).checkUserPermissions(USER, experiment.getApplicationName(), Permission.CREATE);
        doNothing().when(priorities).setPriority(experiment.getID(), 1);

        Response response = experimentsResource.setPriority(experiment.getID(), 1, AUTHHEADER);
        assertEquals("Response code indicates priority was not set.", response.getStatus(), CREATED.getStatusCode());
    }