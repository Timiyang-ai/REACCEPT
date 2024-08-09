    @Test(expected = RepositoryException.class)
    public void getImpressionRowsTest() {
        Experiment.ID experimentId = Experiment.ID.newInstance();
        Parameters parameters = mock(Parameters.class, RETURNS_DEEP_STUBS);
        when(parameters.getContext().getContext()).thenReturn("TEST");
        Date from = mock(Date.class), to = mock(Date.class);
        when(parameters.getFromTime()).thenReturn(from);
        when(parameters.getToTime()).thenReturn(to);
        List<Map> expected = mock(List.class);
        when(transaction.select(anyString(), Matchers.anyVararg())).thenReturn(expected);
        List<Map> result = databaseAnalytics.getImpressionRows(experimentId, parameters);
        assertThat(result, is(expected));
        //exception while select
        doThrow(new RuntimeException()).when(transaction)
                .select(anyString(), Matchers.anyVararg());
        databaseAnalytics.getImpressionRows(experimentId, parameters);
        fail();
    }