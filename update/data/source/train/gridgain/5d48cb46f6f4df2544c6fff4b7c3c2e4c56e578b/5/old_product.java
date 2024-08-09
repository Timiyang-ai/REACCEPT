public void reactivateSimple(int srvs, int clients, int activateFrom) throws Exception {
        activateSimple(srvs, clients, activateFrom);

        rolloverSegmentAtLeastTwice(activateFrom);

        for (int i = 0; i < srvs + clients; i++) {
            for (int c = 0; c < DEFAULT_CACHES_COUNT; c++)
                checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);

            checkCache(ignite(i), CU.UTILITY_CACHE_NAME, true);
        }

        ignite(activateFrom).cluster().active(false);

        ignite(activateFrom).cluster().active(true);

        rolloverSegmentAtLeastTwice(activateFrom);

        for (int i = 0; i < srvs + clients; i++) {
            for (int c = 0; c < DEFAULT_CACHES_COUNT; c++)
                checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);

            checkCache(ignite(i), CU.UTILITY_CACHE_NAME, true);
        }
    }