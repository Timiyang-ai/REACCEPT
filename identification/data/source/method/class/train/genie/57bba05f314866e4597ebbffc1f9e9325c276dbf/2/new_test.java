@Test
    public void testCreateCluster() throws GenieException {
        final Set<String> configs = new HashSet<>();
        configs.add("a config");
        configs.add("another config");
        configs.add("yet another config");
        final Cluster cluster = new Cluster(
                CLUSTER_1_NAME,
                CLUSTER_1_USER,
                CLUSTER_1_VERSION,
                ClusterStatus.OUT_OF_SERVICE,
                CLUSTER_1_TYPE
        );
        cluster.setConfigs(configs);
        final String id = UUID.randomUUID().toString();
        cluster.setId(id);
        final Cluster created = this.service.createCluster(cluster);
        Assert.assertNotNull(this.service.getCluster(id));
        Assert.assertEquals(id, created.getId());
        Assert.assertEquals(CLUSTER_1_NAME, created.getName());
        Assert.assertEquals(CLUSTER_1_USER, created.getUser());
        Assert.assertEquals(ClusterStatus.OUT_OF_SERVICE, created.getStatus());
        Assert.assertEquals(CLUSTER_1_TYPE, created.getClusterType());
        Assert.assertEquals(3, created.getConfigs().size());
        this.service.deleteCluster(id);
        try {
            this.service.getCluster(id);
            Assert.fail("Should have thrown exception");
        } catch (final GenieException ge) {
            Assert.assertEquals(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    ge.getErrorCode()
            );
        }
    }