    @Test
    public void postExperiment() throws Exception {
        NewExperiment newExperiment = NewExperiment.withID(Experiment.ID.newInstance())
                .withAppName(TESTAPP)
                .withLabel(Experiment.Label.valueOf("label"))
                .withStartTime(date)
                .withEndTime(date)
                .withSamplingPercent(.90)
                .withDescription(description)
                .build();
        try {
            newExperiment.setApplicationName(null);
            experimentsResource.postExperiment(newExperiment, false, AUTHHEADER);
            fail();
        } catch (IllegalArgumentException ignored) {
        }

        newExperiment.setApplicationName(TESTAPP);
        doThrow(AuthenticationException.class).when(authorization).getUser(null);
        try {
            experimentsResource.postExperiment(newExperiment, false, null);
            fail();
        } catch (AuthenticationException ignored) {
        }

        Experiment experiment1 = Experiment.withID(newExperiment.getID())
                .withApplicationName(newExperiment.getApplicationName())
                .withEndTime(newExperiment.getEndTime())
                .withStartTime(newExperiment.getStartTime())
                .withLabel(newExperiment.getLabel())
                .build();

        when(experiments.getExperiment(newExperiment.getID())).thenReturn(experiment1);
        Response response = experimentsResource.postExperiment(newExperiment, false, AUTHHEADER);
        Assert.assertEquals(experiment1, response.getEntity());

        // When user(TESTUSER) doesn't have create permissions we throw an exception
        when(authorization.getUser(AUTHHEADER)).thenReturn(TESTUSER);
        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(TESTUSER, TESTAPP, Permission.CREATE);
        try {
            experimentsResource.postExperiment(newExperiment, false, null);
            fail();
        } catch (AuthenticationException ignored) {
        }

        // When user(TESTUSER) doesn't have create permissions but flags is true
        // we create a new application and add him as ADMIN_LABEL
        when(authorization.getUser(AUTHHEADER)).thenReturn(TESTUSER);
        when(experiments.getExperiment(newExperiment.getID())).thenReturn(experiment1);
        Response responseNewApp = experimentsResource.postExperiment(newExperiment, true, AUTHHEADER);
        Assert.assertEquals(experiment1, responseNewApp.getEntity());

        // When no AUTHHEADER is present
        doThrow(AuthenticationException.class).when(authorization)
                .getUser(AUTHHEADER);
        try {
            experimentsResource.postExperiment(newExperiment, false, null);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }