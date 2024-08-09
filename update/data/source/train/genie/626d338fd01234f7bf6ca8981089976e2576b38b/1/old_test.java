@Test
    public void testCreateCluster() throws GenieException {
        final Set<String> configs = Sets.newHashSet("a config", "another config", "yet another config");
        final Set<String> dependencies = Sets.newHashSet("a dependency");
        final String id = UUID.randomUUID().toString();
        final Cluster cluster = new Cluster.Builder(
            CLUSTER_1_NAME,
            CLUSTER_1_USER,
            CLUSTER_1_VERSION,
            ClusterStatus.OUT_OF_SERVICE
        )
            .withId(id)
            .withConfigs(configs)
            .withDependencies(dependencies)
            .build();
        this.service.createCluster(cluster);
        final Cluster created = this.service.getCluster(id);
        Assert.assertNotNull(created);
        Assert.assertEquals(id, created.getId().orElseThrow(IllegalArgumentException::new));
        Assert.assertEquals(CLUSTER_1_NAME, created.getName());
        Assert.assertEquals(CLUSTER_1_USER, created.getUser());
        Assert.assertEquals(ClusterStatus.OUT_OF_SERVICE, created.getStatus());
        Assert.assertEquals(3, created.getConfigs().size());
        Assert.assertEquals(1, created.getDependencies().size());
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