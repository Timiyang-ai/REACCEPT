@Test
    public void sendHeartbeatRequest()
            throws Exception {
        byte[] buffer = client.sendHeartbeatRequest().get();
        assertThat(NodeMetrics.parseFrom(buffer)).isNotNull();
    }