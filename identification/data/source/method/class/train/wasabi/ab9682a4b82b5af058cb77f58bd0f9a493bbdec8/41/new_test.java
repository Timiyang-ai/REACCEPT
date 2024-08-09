    @Test
    public void getBuckets() throws Exception {
        Bucket bucket1 = Bucket.newInstance(experiment.getID(), Bucket.Label.valueOf("bar"))
                .withAllocationPercent(.5)
                .withControl(false)
                .withDescription("")
                .withPayload("")
                .build();

        BucketList bucketList = new BucketList();
        bucketList.addBucket(bucket);
        bucketList.addBucket(bucket1);

        when(buckets.getBuckets(experiment.getID(), true)).thenReturn(bucketList);
        Response response = experimentsResource.getBuckets(experiment.getID(), null);
        Assert.assertEquals("case 1", bucketList, response.getEntity());

        when(authorization.getUser(AUTHHEADER)).thenReturn(USER);
        when(experiments.getExperiment(experiment.getID())).thenReturn(experiment);
        response = experimentsResource.getBuckets(experiment.getID(), AUTHHEADER);
        Assert.assertEquals("case 2", bucketList, response.getEntity());

        doThrow(AuthenticationException.class).when(authorization)
                .checkUserPermissions(USER, TESTAPP, Permission.READ);
        try {
            experimentsResource.getBuckets(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }

        doThrow(AuthenticationException.class).when(authorization).getUser(AUTHHEADER);
        try {
            experimentsResource.getBuckets(experiment.getID(), AUTHHEADER);
            fail();
        } catch (AuthenticationException ignored) {
        }
    }