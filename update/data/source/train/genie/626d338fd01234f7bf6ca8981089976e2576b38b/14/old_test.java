@Test
    public void testGetCluster() throws GenieException {
        final Cluster cluster1 = this.service.getCluster(CLUSTER_1_ID);
        Assert.assertEquals(CLUSTER_1_ID, cluster1.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(CLUSTER_1_NAME, cluster1.getName());
        Assert.assertEquals(CLUSTER_1_USER, cluster1.getUser());
        Assert.assertEquals(CLUSTER_1_VERSION, cluster1.getVersion());
        Assert.assertEquals(CLUSTER_1_STATUS, cluster1.getStatus());
        Assert.assertEquals(5, cluster1.getTags().size());
        Assert.assertEquals(1, cluster1.getConfigs().size());
        Assert.assertEquals(2, cluster1.getDependencies().size());

        final Cluster cluster2 = this.service.getCluster(CLUSTER_2_ID);
        Assert.assertEquals(CLUSTER_2_ID, cluster2.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(CLUSTER_2_NAME, cluster2.getName());
        Assert.assertEquals(CLUSTER_2_USER, cluster2.getUser());
        Assert.assertEquals(CLUSTER_2_VERSION, cluster2.getVersion());
        Assert.assertEquals(CLUSTER_2_STATUS, cluster2.getStatus());
        Assert.assertEquals(5, cluster2.getTags().size());
        Assert.assertEquals(2, cluster2.getConfigs().size());
        Assert.assertEquals(0, cluster2.getDependencies().size());
    }