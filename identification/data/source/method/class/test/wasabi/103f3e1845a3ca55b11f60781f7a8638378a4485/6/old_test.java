    @Test
    public void getExperiments() throws Exception {
        doReturn(responseBuilder).when(httpHeader).headers();
        doReturn(responseBuilder).when(responseBuilder).entity(anyCollection());
        doReturn(response).when(responseBuilder).build();

        applicationsResource.getExperiments(applicationName, "foo");

        verify(authorizedExperimentGetter).getExperimentsByName(true, "foo", applicationName);
        verify(httpHeader).headers();
        verify(responseBuilder).build();
    }