@Test
    public void restartTest() throws Exception {

        final int PORT_0 = 9000;

        Process corfuServer = runSinglePersistentServer(corfuSingleNodeHost, PORT_0);

        CorfuRuntime corfuRuntime = createDefaultRuntime();
        Layout l = incrementClusterEpoch(corfuRuntime);
        corfuRuntime.getLayoutView().getEpochedClient(l).getBaseClient("localhost:9000")
                .restart().get();

        restartServer(corfuRuntime, DEFAULT_ENDPOINT);

        assertThat(corfuRuntime.getLayoutView().getLayout().getEpoch())
                .isGreaterThanOrEqualTo(l.getEpoch() + 1);
        assertThat(shutdownCorfuServer(corfuServer)).isTrue();
    }