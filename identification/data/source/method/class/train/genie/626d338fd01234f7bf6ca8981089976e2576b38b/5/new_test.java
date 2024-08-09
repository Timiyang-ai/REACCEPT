@Test
    public void testGetCluster() throws GenieException {
        final Cluster cluster1 = this.service.getCluster(CLUSTER_1_ID);
        Assert.assertEquals(CLUSTER_1_ID, cluster1.getId());
        Assert.assertEquals(CLUSTER_1_NAME, cluster1.getMetadata().getName());
        Assert.assertEquals(CLUSTER_1_USER, cluster1.getMetadata().getUser());
        Assert.assertEquals(
            CLUSTER_1_VERSION,
            cluster1.getMetadata().getVersion().orElse(UUID.randomUUID().toString())
        );
        Assert.assertEquals(CLUSTER_1_STATUS, cluster1.getMetadata().getStatus());
        Assert.assertEquals(3, cluster1.getMetadata().getTags().size());
        Assert.assertEquals(1, cluster1.getResources().getConfigs().size());
        Assert.assertEquals(2, cluster1.getResources().getDependencies().size());

        final Cluster cluster2 = this.service.getCluster(CLUSTER_2_ID);
        Assert.assertEquals(CLUSTER_2_ID, cluster2.getId());
        Assert.assertEquals(CLUSTER_2_NAME, cluster2.getMetadata().getName());
        Assert.assertEquals(CLUSTER_2_USER, cluster2.getMetadata().getUser());
        Assert.assertEquals(
            CLUSTER_2_VERSION,
            cluster2.getMetadata().getVersion().orElse(UUID.randomUUID().toString())
        );
        Assert.assertEquals(CLUSTER_2_STATUS, cluster2.getMetadata().getStatus());
        Assert.assertEquals(3, cluster2.getMetadata().getTags().size());
        Assert.assertEquals(2, cluster2.getResources().getConfigs().size());
        Assert.assertEquals(0, cluster2.getResources().getDependencies().size());
    }