    @Test
    public void getExclusions() throws Exception {
        Experiment experiment2 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP)
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withState(Experiment.State.DRAFT)
                .build();
        Experiment experiment3 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP)
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withState(Experiment.State.RUNNING)
                .build();
        Experiment experiment4 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP)
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withState(Experiment.State.TERMINATED)
                .build();
        Experiment experiment5 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(TESTAPP)
                .withStartTime(new Date())
                .withEndTime(new Date())
                .withState(Experiment.State.DELETED)
                .build();

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.getExclusions(experiment.getID(), true, true, AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);

        ExperimentList experimentList = new ExperimentList();
        experimentList.addExperiment(experiment2);
        experimentList.addExperiment(experiment3);
        experimentList.addExperiment(experiment4);
        experimentList.addExperiment(experiment5);

        ExperimentList experimentListResponse = new ExperimentList();
        experimentListResponse.addExperiment(experiment2);
        experimentListResponse.addExperiment(experiment3);

        when(mutex.getExclusions(experiment.getID())).thenReturn(experimentList);
        when(mutex.getNotExclusions(experiment.getID())).thenReturn(experimentList);

        Response response = experimentsResource.getExclusions(experiment.getID(), true, true, AUTHHEADER);
        Assert.assertEquals("case 1", experimentList, response.getEntity());
        response = experimentsResource.getExclusions(experiment.getID(), true, false, AUTHHEADER);
        Assert.assertEquals("case 2", experimentList, response.getEntity());
        response = experimentsResource.getExclusions(experiment.getID(), false, true, AUTHHEADER);
        Assert.assertEquals("case 3", experimentListResponse, response.getEntity());
        response = experimentsResource.getExclusions(experiment.getID(), false, false, AUTHHEADER);
        Assert.assertEquals("case 4", experimentListResponse, response.getEntity());

        response = experimentsResource.getExclusions(experiment.getID(), true, true, null);
        Assert.assertEquals("case 5", experimentList, response.getEntity());
        response = experimentsResource.getExclusions(experiment.getID(), true, false, null);
        Assert.assertEquals("case 6", experimentList, response.getEntity());
        response = experimentsResource.getExclusions(experiment.getID(), false, true, null);
        Assert.assertEquals("case 7", experimentListResponse, response.getEntity());
        response = experimentsResource.getExclusions(experiment.getID(), false, false, null);
        Assert.assertEquals("case 8", experimentListResponse, response.getEntity());

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.READ);
        try {
            experimentsResource.getExclusions(experiment.getID(), true, true, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization)
                .getUser(AUTHHEADER);
        try {
            experimentsResource.getExclusions(experiment.getID(), true, true, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }