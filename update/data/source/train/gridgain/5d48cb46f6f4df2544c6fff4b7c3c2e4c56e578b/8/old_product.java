private void activateSimple(int srvs, int clients, int activateFrom) throws Exception {
        active = false;

        final int CACHES = 2;

        for (int i = 0; i < srvs + clients; i++) {
            client = i >= srvs;

            ccfgs = cacheConfigurations1();

            startGrid(i);

            checkNoCaches(i);
        }

        for (int i = 0; i < srvs + clients; i++)
            assertFalse(ignite(i).cluster().active());

        ignite(activateFrom).cluster().active(false); // Should be no-op.

        ignite(activateFrom).cluster().active(true);

        for (int i = 0; i < srvs + clients; i++)
            assertTrue(ignite(i).cluster().active());

        for (int i = 0; i < srvs + clients; i++) {
            for (int c = 0; c < DEFAULT_CACHES_COUNT; c++)
                checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);

            checkCache(ignite(i), CU.UTILITY_CACHE_NAME, true);
        }

        checkCaches(srvs + clients, CACHES);

        client = false;

        startGrid(srvs + clients);

        for (int c = 0; c < DEFAULT_CACHES_COUNT; c++)
            checkCache(ignite(srvs + clients), CACHE_NAME_PREFIX + c, true);

        checkCaches(srvs + clients + 1, CACHES);

        client = true;

        startGrid(srvs + clients + 1);

        for (int c = 0; c < DEFAULT_CACHES_COUNT; c++)
            checkCache(ignite(srvs + clients + 1), CACHE_NAME_PREFIX + c, false);

        checkCaches(srvs + clients + 2, CACHES);
    }