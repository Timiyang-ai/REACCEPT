@Test
    public void testSetClusters() {
        Assert.assertNotNull(this.c.getClusters());
        Assert.assertTrue(this.c.getClusters().isEmpty());
        final Set<Cluster> clusters = new HashSet<>();
        clusters.add(new Cluster());
        this.c.setClusters(clusters);
        Assert.assertEquals(clusters, this.c.getClusters());
    }