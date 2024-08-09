@Test
    public void testCreateCluster() throws GenieException {
        final Set<String> configs = Sets.newHashSet("a config", "another config", "yet another config");
        final Set<String> dependencies = Sets.newHashSet("a dependency");
        final String id = UUID.randomUUID().toString();
        final ClusterRequest cluster = new ClusterRequest.Builder(
            new ClusterMetadata.Builder(
                CLUSTER_1_NAME,
                CLUSTER_1_USER,
                ClusterStatus.OUT_OF_SERVICE
            )
                .withVersion(CLUSTER_1_VERSION)
                .build()
        )
            .withRequestedId(id)
            .withResources(new ExecutionEnvironment(configs, dependencies, null))
            .build();
        this.service.createCluster(cluster);
        final Cluster created = this.service.getCluster(id);
        Assert.assertNotNull(created);
        Assert.assertEquals(id, created.getId());
        Assert.assertEquals(CLUSTER_1_NAME, created.getMetadata().getName());
        Assert.assertEquals(CLUSTER_1_USER, created.getMetadata().getUser());
        Assert.assertEquals(ClusterStatus.OUT_OF_SERVICE, created.getMetadata().getStatus());
        Assert.assertEquals(3, created.getResources().getConfigs().size());
        Assert.assertEquals(1, created.getResources().getDependencies().size());
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