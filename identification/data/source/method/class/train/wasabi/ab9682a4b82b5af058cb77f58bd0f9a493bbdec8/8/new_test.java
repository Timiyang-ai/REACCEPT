    @Test
    public void getExperiment() throws Exception {
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.getExperiment(experiment.getID(), null);
            fail();
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        Response response = experimentsResource.getExperiment(experiment.getID(), null);
        Assert.assertEquals("case 1", experiment, response.getEntity());

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        response = experimentsResource.getExperiment(experiment.getID(), AUTHHEADER);
        Assert.assertEquals("case 2", experiment, response.getEntity());

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, TESTAPP, Permission.READ);
        try {
            experimentsResource.getExperiment(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization)
                .getUser(AUTHHEADER);
        try {
            experimentsResource.getExperiment(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }