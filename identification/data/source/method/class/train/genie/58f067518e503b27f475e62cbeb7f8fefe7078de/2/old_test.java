@Test
    public void testDeleteTerminatedClusters() throws GenieException, IOException {
        Assert.assertThat(this.clusterRepository.count(), Matchers.is(2L));
        final String testClusterId = UUID.randomUUID().toString();
        final Cluster testCluster = new Cluster.Builder(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            ClusterStatus.OUT_OF_SERVICE
        )
            .withId(testClusterId)
            .withConfigs(Sets.newHashSet(UUID.randomUUID().toString()))
            .withDependencies(Sets.newHashSet(UUID.randomUUID().toString()))
            .withSetupFile(UUID.randomUUID().toString())
            .withTags(Sets.newHashSet(UUID.randomUUID().toString(), UUID.randomUUID().toString()))
            .build();
        this.service.createCluster(testCluster);

        // Shouldn't delete any clusters as all are UP or OOS
        Assert.assertThat(this.service.deleteTerminatedClusters(), Matchers.is(0));

        // Change status to UP
        String patchString = "[{ \"op\": \"replace\", \"path\": \"/status\", \"value\": \"UP\" }]";
        JsonPatch patch = JsonPatch.fromJson(GenieObjectMapper.getMapper().readTree(patchString));
        this.service.patchCluster(testClusterId, patch);
        Assert.assertThat(this.service.getCluster(testClusterId).getStatus(), Matchers.is(ClusterStatus.UP));

        // All clusters are UP/OOS or attached to jobs
        Assert.assertThat(this.service.deleteTerminatedClusters(), Matchers.is(0));

        // Change status to terminated
        patchString = "[{ \"op\": \"replace\", \"path\": \"/status\", \"value\": \"TERMINATED\" }]";
        patch = JsonPatch.fromJson(GenieObjectMapper.getMapper().readTree(patchString));
        this.service.patchCluster(testClusterId, patch);
        Assert.assertThat(this.service.getCluster(testClusterId).getStatus(), Matchers.is(ClusterStatus.TERMINATED));

        // All clusters are UP/OOS or attached to jobs
        Assert.assertThat(this.service.deleteTerminatedClusters(), Matchers.is(1));

        // Make sure it didn't delete any of the clusters we wanted
        Assert.assertTrue(this.clusterRepository.existsByUniqueId(CLUSTER_1_ID));
        Assert.assertTrue(this.clusterRepository.existsByUniqueId(CLUSTER_2_ID));
        Assert.assertFalse(this.clusterRepository.existsByUniqueId(testClusterId));
    }