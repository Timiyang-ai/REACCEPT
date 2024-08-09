    @Test
    public void removeExclusions() throws Exception {
        Experiment experiment2 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP)
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withState(Experiment.State.DRAFT)
                .build();

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.removeExclusions(experiment.getID(), experiment2.getID(), AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.DELETE);
        try {
            experimentsResource.removeExclusions(experiment.getID(), experiment2.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization)
                .getUser(AUTHHEADER);
        try {
            experimentsResource.removeExclusions(experiment.getID(), experiment2.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.removeExclusions(experiment.getID(), experiment2.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        // simply pass through the method when we get valid input and check if the response is correct
        doReturn(USER).when(authorization).getUser(AUTHHEADER);
        doReturn(experiment).when(experiments).getExperiment(experiment.getID());
        doNothing().when(authorization).checkUserPermissions(USER, experiment.getApplicationName(), Permission.DELETE);
        doNothing().when(mutex).deleteExclusion(experiment.getID(), experiment2.getID(), USERINFO);

        Response response = experimentsResource.removeExclusions(experiment.getID(), experiment2.getID(), AUTHHEADER);
        assertEquals("Response code indicates no deletion occurred.", response.getStatus(), NO_CONTENT.getStatusCode());
    }