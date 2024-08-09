@Test
    public void testGetCluster() throws GenieException {
        final Cluster cluster1 = this.service.getCluster(CLUSTER_1_ID);
        Assert.assertEquals(CLUSTER_1_ID, cluster1.getId());
        Assert.assertEquals(CLUSTER_1_NAME, cluster1.getName());
        Assert.assertEquals(CLUSTER_1_USER, cluster1.getUser());
        Assert.assertEquals(CLUSTER_1_VERSION, cluster1.getVersion());
        Assert.assertEquals(CLUSTER_1_STATUS, cluster1.getStatus());
        Assert.assertEquals(CLUSTER_1_TYPE, cluster1.getClusterType());
        Assert.assertEquals(5, cluster1.getTags().size());
        Assert.assertEquals(1, cluster1.getConfigs().size());
        Assert.assertEquals(3, cluster1.getCommands().size());

        final Cluster cluster2 = this.service.getCluster(CLUSTER_2_ID);
        Assert.assertEquals(CLUSTER_2_ID, cluster2.getId());
        Assert.assertEquals(CLUSTER_2_NAME, cluster2.getName());
        Assert.assertEquals(CLUSTER_2_USER, cluster2.getUser());
        Assert.assertEquals(CLUSTER_2_VERSION, cluster2.getVersion());
        Assert.assertEquals(CLUSTER_2_STATUS, cluster2.getStatus());
        Assert.assertEquals(CLUSTER_2_TYPE, cluster2.getClusterType());
        Assert.assertEquals(5, cluster2.getTags().size());
        Assert.assertEquals(2, cluster2.getConfigs().size());
        Assert.assertEquals(3, cluster1.getCommands().size());
    }