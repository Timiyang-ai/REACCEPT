    @Test(expected = ExperimentNotFoundException.class)
    public void getExperimentIfExistsTest() {
        Experiment.ID id = Experiment.ID.newInstance();
        Experiment experiment = mock(Experiment.class);
        when(experiments.getExperiment(eq(id))).thenReturn(experiment);
        Experiment result = this.analyticsImpl.getExperimentIfExists(id);
        assertThat(result, is(experiment));
        when(experiments.getExperiment(not(eq(id)))).thenReturn(null);
        result = this.analyticsImpl.getExperimentIfExists(Experiment.ID.newInstance());
        Assert.fail();
    }