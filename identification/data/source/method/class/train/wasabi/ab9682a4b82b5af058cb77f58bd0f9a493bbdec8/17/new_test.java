    @Test
    public void createExclusions() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserInfo(USER)).thenReturn(USERINFO);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        Experiment experiment2 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP)
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withState(Experiment.State.DRAFT)
                .build();
        List<Experiment.ID> experimentIDs = new ArrayList<>();
        experimentIDs.add(experiment2.getID());
        ExperimentIDList experimentIDList = ExperimentIDList.newInstance().withExperimentIDs(experimentIDs).build();
        try {
            experimentsResource.createExclusions(experiment.getID(), experimentIDList, AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        HashMap<Experiment.ID, Experiment.ID> hashMap = new HashMap<>();
        hashMap.put(experiment.getID(), experiment2.getID());
        List<Map> exclusionsList = new ArrayList<>();
        exclusionsList.add(hashMap);
        when(mutex.createExclusions(experiment.getID(), experimentIDList, USERINFO)).thenReturn(exclusionsList);
        HashMap<String, Object> result = new HashMap<>();
        result.put("exclusions", exclusionsList);
        Response response = experimentsResource.createExclusions(experiment.getID(), experimentIDList, AUTHHEADER);
        Assert.assertEquals(result, response.getEntity());

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.CREATE);
        try {
            experimentsResource.createExclusions(experiment.getID(), experimentIDList, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }