    @Test(expected = RepositoryException.class)
    public void getJointActionsTest() {
        Experiment.ID experimentId = Experiment.ID.newInstance();
        Parameters parameters = mock(Parameters.class, RETURNS_DEEP_STUBS);
        when(parameters.getContext().getContext()).thenReturn("TEST");
        Date from = mock(Date.class), to = mock(Date.class);
        when(parameters.getFromTime()).thenReturn(from);
        when(parameters.getToTime()).thenReturn(to);
        List<String> actions = new ArrayList<String>();
        actions.add("TEST_ACTION");
        when(parameters.getActions()).thenReturn(actions);
        List<Map> expected = mock(List.class);
        when(transaction.select(anyString(), Matchers.anyVararg())).thenReturn(expected);
        List<Map> result = databaseAnalytics.getJointActions(experimentId, parameters);
        assertThat(result, is(expected));
        //exception while select
        doThrow(new RuntimeException()).when(transaction)
                .select(anyString(), Matchers.anyVararg());
        databaseAnalytics.getJointActions(experimentId, parameters);
        fail();
    }