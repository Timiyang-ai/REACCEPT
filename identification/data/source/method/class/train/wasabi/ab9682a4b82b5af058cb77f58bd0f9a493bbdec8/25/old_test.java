    @Test
    public void putBucket() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserInfo(USER)).thenReturn(USERINFO);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);

        try {
            experimentsResource.putBucket(experiment.getID(), bucket.getLabel(), bucket, AUTHHEADER);
            fail();
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        when(buckets.updateBucket(experiment.getID(), bucket.getLabel(), bucket, USERINFO)).thenReturn(null);

        try {
            experimentsResource.putBucket(experiment.getID(), bucket.getLabel(), bucket, AUTHHEADER);
            fail();
        } catch (AssertionError ignored) {
        }


        when(buckets.updateBucket(experiment.getID(), bucket.getLabel(), bucket, USERINFO)).thenReturn(bucket);
        Response response = experimentsResource.putBucket(experiment.getID(), bucket.getLabel(), bucket, AUTHHEADER);
        Assert.assertEquals(bucket, response.getEntity());

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.UPDATE);
        try {
            experimentsResource.putBucket(experiment.getID(), bucket.getLabel(), bucket, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.putBucket(experiment.getID(), bucket.getLabel(), bucket, AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }