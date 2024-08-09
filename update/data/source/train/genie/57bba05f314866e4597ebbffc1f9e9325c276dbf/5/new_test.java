@Test
    public void testOnCreateOrUpdateCluster() throws GeniePreconditionException {
        this.c = new Cluster(NAME, USER, VERSION, ClusterStatus.UP, CLUSTER_TYPE);
        Assert.assertNull(this.c.getTags());
        this.c.onCreateOrUpdateCluster();
        Assert.assertEquals(2, this.c.getTags().size());
    }