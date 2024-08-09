@Test
    public void testPatchCluster() throws GenieException, IOException {
        final Cluster getCluster = this.service.getCluster(CLUSTER_1_ID);
        Assert.assertThat(getCluster.getName(), Matchers.is(CLUSTER_1_NAME));
        final Date updateTime = getCluster.getUpdated().orElseThrow(IllegalArgumentException::new);

        final String patchString
            = "[{ \"op\": \"replace\", \"path\": \"/name\", \"value\": \"" + CLUSTER_2_NAME + "\" }]";
        final ObjectMapper mapper = new ObjectMapper();
        final JsonPatch patch = JsonPatch.fromJson(mapper.readTree(patchString));

        this.service.patchCluster(CLUSTER_1_ID, patch);

        final Cluster updated = this.service.getCluster(CLUSTER_1_ID);
        Assert.assertNotEquals(updated.getUpdated(), Matchers.is(updateTime));
        Assert.assertThat(updated.getName(), Matchers.is(CLUSTER_2_NAME));
    }