    @Test
    public void putBucketState() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(authorization.getUserInfo(USER)).thenReturn(USERINFO);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.putBucketState(experiment.getID(), bucket.getLabel(), Bucket.State.valueOf("OPEN"),
                    AUTHHEADER);
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        when(buckets.updateBucketState(experiment.getID(), bucket.getLabel(), Bucket.State.valueOf("OPEN"),
                USERINFO)).thenReturn(null);
        try {
            experimentsResource.putBucketState(experiment.getID(), bucket.getLabel(), Bucket.State.valueOf("OPEN"),
                    AUTHHEADER);
        } catch (AssertionError ignored) {
        }

        when(buckets.updateBucketState(experiment.getID(), bucket.getLabel(), Bucket.State.valueOf("OPEN"),
                USERINFO)).thenReturn(bucket);

        Response response = experimentsResource.putBucketState(experiment.getID(), bucket.getLabel(), Bucket.State.valueOf("OPEN"),
                AUTHHEADER);
        Assert.assertEquals(bucket, response.getEntity());

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.UPDATE);
        try {
            experimentsResource.putBucketState(experiment.getID(), bucket.getLabel(), Bucket.State.valueOf("OPEN"),
                    AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.putBucketState(experiment.getID(), bucket.getLabel(), Bucket.State.valueOf("OPEN"),
                    AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }