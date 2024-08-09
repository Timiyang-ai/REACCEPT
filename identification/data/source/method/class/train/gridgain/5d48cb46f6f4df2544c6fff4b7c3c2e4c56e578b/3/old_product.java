private void deactivateSimple(int srvs, int clients, int deactivateFrom) throws Exception {
        active = true;

        final int CACHES = 2;

        for (int i = 0; i < srvs + clients; i++) {
            client = i >= srvs;

            ccfgs = cacheConfigurations1();

            startGrid(i);
        }

        ignite(deactivateFrom).cluster().active(true); // Should be no-op.

        checkCaches(srvs + clients, CACHES);

        for (int i = 0; i < srvs + clients; i++)
            assertTrue(ignite(i).cluster().active());

        ignite(deactivateFrom).cluster().active(false);

        for (int i = 0; i < srvs + clients; i++)
            assertFalse(ignite(i).cluster().active());

        checkNoCaches(srvs + clients);

        client = false;

        startGrid(srvs + clients);

        checkNoCaches(srvs + clients + 1);

        client = true;

        startGrid(srvs + clients + 1);

        checkNoCaches(srvs + clients + 2);

        for (int i = 0; i < srvs + clients + 2; i++)
            assertFalse(ignite(i).cluster().active());

        ignite(deactivateFrom).cluster().active(true);

        for (int i = 0; i < srvs + clients + 2; i++) {
            assertTrue(ignite(i).cluster().active());

            checkCache(ignite(i), CU.UTILITY_CACHE_NAME, true);
        }

        for (int i = 0; i < srvs; i++) {
            for (int c = 0; c < DEFAULT_CACHES_COUNT; c++)
                checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);
        }

        checkCaches(srvs + clients + 2);
    }