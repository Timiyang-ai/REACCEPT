@Test
    public void testOnCreateOrUpdateCluster() throws GenieException {
        this.c = new Cluster(NAME, USER, ClusterStatus.UP, CLUSTER_TYPE, this.configs, VERSION);
        this.c.onCreateOrUpdateCluster();
    }