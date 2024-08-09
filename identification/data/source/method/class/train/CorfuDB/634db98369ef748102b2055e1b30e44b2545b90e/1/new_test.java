@Test
    public void sendHeartbeatRequest()
            throws Exception {
        NodeView nodeView = client.sendHeartbeatRequest().get();
        assertThat(nodeView).isNotNull();
    }