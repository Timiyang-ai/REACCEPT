@Test
    public void resetTest() throws Exception {

        final int PORT_0 = 9000;

        Process corfuServer = runSinglePersistentServer(corfuSingleNodeHost, PORT_0);

        CorfuRuntime corfuRuntime = createDefaultRuntime();
        incrementClusterEpoch(corfuRuntime);
        corfuRuntime.getLayoutView().getEpochedClient().getBaseClient("localhost:9000")
                .reset().get();

        corfuRuntime = createDefaultRuntime();
        // The shutdown and reset can take an unknown amount of time and there is a chance that the
        // newer runtime may also connect to the older corfu server (before reset).
        // Hence the while loop.
        for (int i = 0; i < PARAMETERS.NUM_ITERATIONS_MODERATE; i++) {
            if (corfuRuntime.getLayoutView().getLayout().getEpoch() == 0L) {
                break;
            }
            Thread.sleep(PARAMETERS.TIMEOUT_SHORT.toMillis());
            corfuRuntime = createDefaultRuntime();
        }
        assertThat(corfuRuntime.getLayoutView().getLayout().getEpoch()).isEqualTo(0L);
        assertThat(shutdownCorfuServer(corfuServer)).isTrue();
    }