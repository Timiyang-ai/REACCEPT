@Test
    public void sendHeartbeatRequest()
            throws Exception {
        ClusterState clusterState = client.sendHeartbeatRequest().get();
        assertThat(clusterState).isNotNull();
    }