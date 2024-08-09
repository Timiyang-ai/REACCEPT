    @Test
    public void exportActions_getExperimentNull() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        thrown.expect(ExperimentNotFoundException.class);
        experimentsResource.exportActions_get(experiment.getID(), AUTHHEADER);
    }