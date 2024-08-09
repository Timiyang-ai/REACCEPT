    @Test
    public void exportAssignments() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        assertNotNull(experimentsResource.exportAssignments(experiment.getID(), context, ignoreStringNullBucket,
                fromStringDate, toStringDate, timeZoneString, AUTHHEADER));
    }