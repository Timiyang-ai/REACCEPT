    @Test
    public void getExperiments() throws Exception {
        Experiment experiment1 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP)
                .withStartTime(date)
                .withEndTime(date)
                .withState(Experiment.State.DRAFT)
                .build();

        Experiment experiment2 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP2)
                .withStartTime(date)
                .withEndTime(date)
                .withState(Experiment.State.DRAFT)
                .build();

        ExperimentList experimentList = new ExperimentList();
        experimentList.addExperiment(experiment);
        experimentList.addExperiment(experiment1);
        experimentList.addExperiment(experiment2);

        when(experiments.getExperiments()).thenReturn(experimentList);

        Response response = experimentsResource.getExperiments(AUTHHEADER, 1, 10, "", "", "", false);

        List responseList = Collections.EMPTY_LIST;
        if (response.getEntity() instanceof HashMap) {
            if (((HashMap) response.getEntity()).get("experiments") instanceof List) {
                responseList = (List) ((HashMap) response.getEntity()).get("experiments");
            }
        }
        Assert.assertEquals("The sizes are different", experimentList.getExperiments().size(), responseList.size());
        Assert.assertTrue("Not all items of the response are in the expected list. (a)",
                experimentList.getExperiments().containsAll(responseList));

        // fewer allowed experiments
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);

        //this throw is so that only the allowed (TESTAPP) experiments get returned
        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, TESTAPP2, Permission.READ);

        response = experimentsResource.getExperiments(AUTHHEADER, 1, 10, "", "", "", false);

        responseList = Collections.EMPTY_LIST;
        if (response.getEntity() instanceof HashMap) {
            if (((HashMap) response.getEntity()).get("experiments") instanceof List) {
                responseList = (List) ((HashMap) response.getEntity()).get("experiments");
            }
        }

        Assert.assertEquals("The sizes is not two", 2, responseList.size());
        Assert.assertTrue("Not all items of the response are in the expected list. (b)",
                experimentList.getExperiments().containsAll(responseList));
        Assert.assertFalse("Response list contains experiment 2!", responseList.contains(experiment2));
    }