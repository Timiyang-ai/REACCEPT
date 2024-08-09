    @Test
    public void putExperiment() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserInfo(USER)).thenReturn(USERINFO);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.putExperiment(experiment.getID(), experiment, false, null);
            fail();
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.updateExperiment(experiment.getID(), experiment, USERINFO)).thenReturn(experiment);
        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        Response response = experimentsResource.putExperiment(experiment.getID(), experiment, false, AUTHHEADER);
        Assert.assertEquals("case 1", experiment, response.getEntity());

        // When a user wants to create a new App and update experiment with it
        experiment.setApplicationName(TESTAPP2);
        when(experiments.updateExperiment(experiment.getID(), experiment, USERINFO)).thenReturn(experiment);
        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        Response responseNewApp = experimentsResource.putExperiment(experiment.getID(), experiment, true, AUTHHEADER);
        Assert.assertEquals("case 2", experiment, responseNewApp.getEntity());

        // When experiment is in deleted state don't allow updates in both cases
        // Old app and new app
        experiment.setState(Experiment.State.DELETED);

        response = experimentsResource.putExperiment(experiment.getID(), experiment, false, AUTHHEADER);
        Assert.assertNull("case 3", response.getEntity());

        response = experimentsResource.putExperiment(experiment.getID(), experiment, true, AUTHHEADER);
        Assert.assertNull("case 4", response.getEntity());

        // Set app name back to TESTAPP
        experiment.setApplicationName(TESTAPP);
        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, TESTAPP, Permission.UPDATE);
        try {
            experimentsResource.putExperiment(experiment.getID(), experiment, false, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization)
                .getUser(AUTHHEADER);
        try {
            experimentsResource.putExperiment(experiment.getID(), experiment, false, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }