@Test
    public void testSetClusters() {
        Assert.assertNull(this.c.getClusters());
        final Set<Cluster> clusters = new HashSet<>();
        clusters.add(new Cluster());
        this.c.setClusters(clusters);
        Assert.assertEquals(clusters, this.c.getClusters());
    }