@Test
    public void testAddTunnelInfo() {
        // initialization
        distrPceStore.storageService = new TestStorageService();
        distrPceStore.activate();

        // TunnelId with device label store information
        distrPceStore.addTunnelInfo(tunnelId1, pceccTunnelInfo1);
        assertThat(distrPceStore.existsTunnelInfo(tunnelId1), is(true));
        assertThat(distrPceStore.getTunnelInfo(tunnelId1), is(pceccTunnelInfo1));
        distrPceStore.addTunnelInfo(tunnelId2, pceccTunnelInfo2);
        assertThat(distrPceStore.existsTunnelInfo(tunnelId2), is(true));
        assertThat(distrPceStore.getTunnelInfo(tunnelId2), is(pceccTunnelInfo2));
    }