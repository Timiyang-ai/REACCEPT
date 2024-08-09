    @Test
    public void getExperimentPages() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.getExperimentPages(experiment.getID(), AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);

        ExperimentPageList experimentPageList = new ExperimentPageList();
        when(pages.getExperimentPages(experiment.getID())).thenReturn(experimentPageList);
        Response response = experimentsResource.getExperimentPages(experiment.getID(), AUTHHEADER);
        Assert.assertEquals("case ", experimentPageList, response.getEntity());
        response = experimentsResource.getExperimentPages(experiment.getID(), null);
        Assert.assertEquals("case ", experimentPageList, response.getEntity());

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.READ);
        try {
            experimentsResource.getExperimentPages(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.getExperimentPages(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }