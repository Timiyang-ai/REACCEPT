    @Test
    public void deletePage() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        experimentsResource.deletePage(experiment.getID(), Page.Name.valueOf("pageName"), AUTHHEADER);
    }