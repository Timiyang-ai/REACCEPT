    @Test(expected = RepositoryException.class)
    public void getRollupRowsTest() {
        Experiment.ID experimentId = Experiment.ID.newInstance();
        String rollupDate = "TEST";
        Parameters parameters = mock(Parameters.class, RETURNS_DEEP_STUBS);
        List<Map> expected = mock(List.class);
        when(parameters.getContext().getContext()).thenReturn("TEST");
        when(transaction.select(anyString(), eq(experimentId), eq(true), eq(rollupDate), anyString()))
                .thenReturn(expected);
        List<Map> result = databaseAnalytics.getRollupRows(experimentId, rollupDate, parameters);
        assertThat(result, is(expected));
        //Exception is thrown in this case
        doThrow(new RuntimeException())
                .when(transaction)
                .select(anyString(), eq(experimentId), eq(true), eq(rollupDate), anyString());
        databaseAnalytics.getRollupRows(experimentId, rollupDate, parameters);
        fail();
    }