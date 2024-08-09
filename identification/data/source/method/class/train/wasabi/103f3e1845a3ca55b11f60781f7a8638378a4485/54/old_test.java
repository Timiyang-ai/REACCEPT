    @Test
    public void getExperiment() throws Exception {
        when(authorizedExperimentGetter.getAuthorizedExperimentByName("foo", applicationName,
                experimentLabel)).thenReturn(experiment);
        whenHttpHeader(experiment);

        applicationsResource.getExperiment(applicationName, experimentLabel, "foo");

        verify(authorizedExperimentGetter).getAuthorizedExperimentByName("foo", applicationName,
                experimentLabel);
        verifyHttpHeader(experiment);
    }