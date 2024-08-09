    @Test
    public void deleteExperiment() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserInfo(USER)).thenReturn(USERINFO);

        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.deleteExperiment(experiment.getID(), AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        Experiment updatedExperiment = Experiment.from(experiment)
                .withState(Experiment.State.DELETED)
                .build();

        when(experiments.updateExperiment(experiment.getID(), updatedExperiment, USERINFO)).thenReturn(null);
        try {
            experimentsResource.deleteExperiment(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AssertionError ignored) {
        }

        when(experiments.updateExperiment(experiment.getID(), updatedExperiment, USERINFO)).thenReturn(updatedExperiment);
        experimentsResource.deleteExperiment(experiment.getID(), AUTHHEADER);

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, TESTAPP, Permission.DELETE);
        try {
            experimentsResource.deleteExperiment(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.deleteExperiment(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }