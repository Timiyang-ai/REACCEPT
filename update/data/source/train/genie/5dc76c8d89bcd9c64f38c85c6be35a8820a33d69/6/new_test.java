@Test
    public void testUpdateCluster() throws Exception {
        final Cluster cluster1 = constructClusterDTO(null);
        clusterClient.createCluster(cluster1);

        final Cluster cluster2 = clusterClient.getCluster(cluster1.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(cluster2.getName(), cluster1.getName());

        final Cluster cluster3 = new
            Cluster.Builder("newname", "newuser", "new version", ClusterStatus.OUT_OF_SERVICE)
            .withId(cluster1.getId().orElseThrow(IllegalArgumentException::new))
            .build();

        clusterClient.updateCluster(cluster1.getId().orElseThrow(IllegalArgumentException::new), cluster3);

        final Cluster cluster4 = clusterClient.getCluster(cluster1.getId().orElseThrow(IllegalArgumentException::new));

        Assert.assertEquals("newname", cluster4.getName());
        Assert.assertEquals("newuser", cluster4.getUser());
        Assert.assertEquals("new version", cluster4.getVersion());
        Assert.assertEquals(ClusterStatus.OUT_OF_SERVICE, cluster4.getStatus());
        Assert.assertFalse(cluster4.getSetupFile().isPresent());
        Assert.assertFalse(cluster4.getDescription().isPresent());
        Assert.assertEquals(Collections.emptySet(), cluster4.getConfigs());
        Assert.assertEquals(cluster4.getTags().contains("foo"), false);
    }