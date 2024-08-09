@Test
    public void testGetTunnelInfo() {
        testAddTunnelInfo();

        // tunnelId1 with device label store info
        assertThat(tunnelId1, is(notNullValue()));
        assertThat(distrPceStore.getTunnelInfo(tunnelId1), is(tunnelConsumerId1));

        // tunnelId2 with device label store info
        assertThat(tunnelId2, is(notNullValue()));
        assertThat(distrPceStore.getTunnelInfo(tunnelId2), is(tunnelConsumerId2));
    }