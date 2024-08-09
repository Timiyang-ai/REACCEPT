@Test
    public void testPatchCluster() throws GenieException, IOException {
        final Cluster getCluster = this.service.getCluster(CLUSTER_1_ID);
        Assert.assertThat(getCluster.getMetadata().getName(), Matchers.is(CLUSTER_1_NAME));
        final Instant updateTime = getCluster.getUpdated();

        final String patchString
            = "[{ \"op\": \"replace\", \"path\": \"/metadata/name\", \"value\": \"" + CLUSTER_2_NAME + "\" }]";
        final JsonPatch patch = JsonPatch.fromJson(GenieObjectMapper.getMapper().readTree(patchString));

        this.service.patchCluster(CLUSTER_1_ID, patch);

        final Cluster updated = this.service.getCluster(CLUSTER_1_ID);
        Assert.assertNotEquals(updated.getUpdated(), Matchers.is(updateTime));
        Assert.assertThat(updated.getMetadata().getName(), Matchers.is(CLUSTER_2_NAME));
    }