@Test
    public void testUpdateCluster() throws Exception {
        final Cluster cluster1 = constructClusterDTO(null);
        clusterClient.createCluster(cluster1);

        final Cluster cluster2 = clusterClient.getCluster(cluster1.getId());
        Assert.assertEquals(cluster2.getName(), cluster1.getName());

        final Cluster cluster3 = new
            Cluster.Builder("newname", "newuser", "new version", ClusterStatus.OUT_OF_SERVICE)
            .withId(cluster1.getId())
            .build();

        clusterClient.updateCluster(cluster1.getId(), cluster3);

        final Cluster cluster4 = clusterClient.getCluster(cluster1.getId());

        Assert.assertEquals("newname", cluster4.getName());
        Assert.assertEquals("newuser", cluster4.getUser());
        Assert.assertEquals("new version", cluster4.getVersion());
        Assert.assertEquals(ClusterStatus.OUT_OF_SERVICE, cluster4.getStatus());
        Assert.assertEquals(null, cluster4.getSetupFile());
        Assert.assertEquals(null, cluster4.getDescription());
        Assert.assertEquals(Collections.emptySet(), cluster4.getConfigs());
        Assert.assertEquals(cluster4.getTags().contains("foo"), false);
    }