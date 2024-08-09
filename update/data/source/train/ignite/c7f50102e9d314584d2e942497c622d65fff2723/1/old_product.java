private void joinWhileActivate1(final boolean startClient, final boolean withNewCache) throws Exception {
        IgniteInternalFuture<?> activeFut = startNodesAndBlockStatusChange(2, 0, 0, false);

        IgniteInternalFuture<?> startFut = GridTestUtils.runAsync((Callable<Void>)() -> {
            client = startClient;

            ccfgs = withNewCache ? cacheConfigurations2() : cacheConfigurations1();

            startGrid(2);

            return null;
        });

        TestRecordingCommunicationSpi spi1 = TestRecordingCommunicationSpi.spi(ignite(1));

        spi1.stopBlock();

        activeFut.get();
        startFut.get();

        for (int c = 0; c < DEFAULT_CACHES_COUNT; c++)
            checkCache(ignite(2), CACHE_NAME_PREFIX + c, true);

        if (withNewCache) {
            for (int i = 0; i < 3; i++) {
                for (int c = 0; c < 4; c++)
                    checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);
            }
        }

        awaitPartitionMapExchange();

        checkCaches(3, withNewCache ? 4 : 2);

        client = false;

        startGrid(3);

        checkCaches(4, withNewCache ? 4 : 2);

        client = true;

        startGrid(4);

        checkCaches(5, withNewCache ? 4 : 2);
    }