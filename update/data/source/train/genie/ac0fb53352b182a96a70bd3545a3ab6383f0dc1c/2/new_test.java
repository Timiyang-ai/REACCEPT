@Test
    public void testOnCreateOrUpdateCluster() throws GenieException {
        this.c = new ClusterEntity(NAME, USER, VERSION, ClusterStatus.UP, CLUSTER_TYPE);
        Assert.assertNotNull(this.c.getTags());
        Assert.assertTrue(this.c.getTags().isEmpty());
        this.c.onCreateOrUpdateCluster();
        Assert.assertEquals(2, this.c.getTags().size());
    }