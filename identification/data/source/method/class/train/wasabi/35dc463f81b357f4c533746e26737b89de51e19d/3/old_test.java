    @Test(expected = RepositoryException.class)
    public void getCountsFromRollupsTest() {
        List<Map> expected = mock(List.class);
        Experiment.ID experimentId = Experiment.ID.newInstance();
        Parameters parameters = mock(Parameters.class, RETURNS_DEEP_STUBS);
        when(parameters.getContext().getContext()).thenReturn("TEST");
        when(transaction.select(anyString(), Matchers.anyVararg())).thenReturn(expected);
        List<Map> result = databaseAnalytics.getCountsFromRollups(experimentId, parameters);
        assertThat(result, is(expected));
        //exception while select
        doThrow(new RuntimeException()).when(transaction)
                .select(anyString(), Matchers.anyVararg());
        databaseAnalytics.getCountsFromRollups(experimentId, parameters);
        fail();
    }