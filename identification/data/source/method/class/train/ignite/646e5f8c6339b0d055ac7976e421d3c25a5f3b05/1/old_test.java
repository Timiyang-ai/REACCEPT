@Test
    public void testReconnectServersRestart_4() throws Exception {
        startGrid(0);

        helper.clientMode(true);

        IgniteEx client = startGrid(1);

        helper.clientMode(false);

        CountDownLatch latch = new CountDownLatch(1);

        client.events().localListen(event -> {
            latch.countDown();

            return true;
        }, EVT_CLIENT_NODE_DISCONNECTED);

        waitForTopology(2);

        stopGrid(0);

        evts.clear();

        // Waiting for client starts to reconnect and create join request.
        assertTrue("Failed to wait for client node disconnected.", latch.await(15, SECONDS));

        // Restart cluster twice for incrementing internal order. (alive zk-nodes having lower order and containing
        // client join request will be removed). See {@link ZookeeperDiscoveryImpl#cleanupPreviousClusterData}.
        startGrid(0);

        stopGrid(0);

        evts.clear();

        startGrid(0);

        waitForTopology(2);
    }