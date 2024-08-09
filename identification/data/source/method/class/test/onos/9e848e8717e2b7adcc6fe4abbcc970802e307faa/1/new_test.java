@Test
    public void testGetTunnelInfos() {
        testAddTunnelInfo();

        Map<TunnelId, ResourceConsumer> tunnelInfoMap = distrPceStore.getTunnelInfos();
        assertThat(tunnelInfoMap, is(notNullValue()));
        assertThat(tunnelInfoMap.isEmpty(), is(false));
        assertThat(tunnelInfoMap.size(), is(2));
    }