    @Test(expected = RepositoryException.class)
    public void checkMostRecentRollupTest() {
        Experiment.ID experimentId = Experiment.ID.newInstance();
        Experiment experiment = mock(Experiment.class);
        when(experiment.getID()).thenReturn(experimentId);
        List queryResult = mock(List.class);
        Parameters parameters = mock(Parameters.class, RETURNS_DEEP_STUBS);
        when(parameters.getContext().getContext()).thenReturn("TEST");
        Date to = mock(Date.class);
        when(to.getTime()).thenReturn(9999L);
        when(transaction.select(anyString(), eq(experimentId), eq("TEST"))).thenReturn(queryResult);
        when(queryResult.isEmpty()).thenReturn(true);
        boolean result = databaseAnalytics.checkMostRecentRollup(experiment, parameters, to);
        assertThat(result, is(true));

        when(queryResult.isEmpty()).thenReturn(false);
        Map map = mock(Map.class);
        Date maxDay = mock(Date.class);
        when(queryResult.get(0)).thenReturn(map);
        when(map.get("day")).thenReturn(maxDay);
        when(maxDay.getTime()).thenReturn(100000L);
        result = databaseAnalytics.checkMostRecentRollup(experiment, parameters, to);
        assertThat(result, is(true));

        when(maxDay.getTime()).thenReturn(1000L);
        result = databaseAnalytics.checkMostRecentRollup(experiment, parameters, to);
        assertThat(result, is(false));

        doThrow(new RuntimeException()).when(transaction)
                .select(anyString(), eq(experimentId), eq("TEST"));
        databaseAnalytics.checkMostRecentRollup(experiment, parameters, to);
        fail();

    }