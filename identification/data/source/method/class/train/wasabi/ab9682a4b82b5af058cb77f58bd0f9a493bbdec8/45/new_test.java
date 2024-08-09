    @Test
    public void getBucket() throws Exception {
        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(null);
        try {
            experimentsResource.getBucket(experiment.getID(), bucket.getLabel(), AUTHHEADER);
            fail();
        } catch (ExperimentNotFoundException ignored) {
        }

        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        when(buckets.getBucket(experiment.getID(), bucket.getLabel())).thenReturn(null);
        try {
            experimentsResource.getBucket(experiment.getID(), bucket.getLabel(), AUTHHEADER);
            fail();
        } catch (BucketNotFoundException ignored) {
        }

        when(buckets.getBucket(experiment.getID(), bucket.getLabel())).thenReturn(bucket);
        Response response = experimentsResource.getBucket(experiment.getID(), bucket.getLabel(), AUTHHEADER);

        Assert.assertEquals(bucket, response.getEntity());


        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, experiment.getApplicationName(), Permission.READ);
        try {
            experimentsResource.getBucket(experiment.getID(), bucket.getLabel(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.getBucket(experiment.getID(), bucket.getLabel(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }