private void activateCachesRestore(int srvs, boolean withNewCaches) throws Exception {
        Map<Integer, Integer> cacheData = startGridsAndLoadData(srvs);

        stopAllGrids();

        for (int i = 0; i < srvs; i++) {
            if (withNewCaches)
                ccfgs = cacheConfigurations2();

            startGrid(i);
        }

        Ignite srv = ignite(0);

        checkNoCaches(srvs);

        srv.cluster().active(true);

        final int CACHES = withNewCaches ? 4 : 2;

        for (int i = 0; i < srvs; i++) {
            for (int c = 0; c < CACHES; c++)
                checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);
        }

        DataStorageConfiguration dsCfg = srv.configuration().getDataStorageConfiguration();

        checkCachesData(cacheData, dsCfg);

        checkCaches(srvs, CACHES);

        int nodes = srvs;

        client = false;

        startGrid(nodes++);

        for (int i = 0; i < nodes; i++) {
            for (int c = 0; c < CACHES; c++)
                checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);
        }

        checkCaches(nodes, CACHES);

        client = true;

        startGrid(nodes++);

        for (int c = 0; c < CACHES; c++)
            checkCache(ignite(nodes - 1), CACHE_NAME_PREFIX + c, false);

        checkCaches(nodes, CACHES);

        for (int i = 0; i < nodes; i++) {
            for (int c = 0; c < CACHES; c++)
                checkCache(ignite(i), CACHE_NAME_PREFIX + c, true);
        }

        checkCachesData(cacheData, dsCfg);
    }