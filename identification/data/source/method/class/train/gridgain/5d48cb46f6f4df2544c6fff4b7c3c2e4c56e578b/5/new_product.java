private void activateCachesRestore(int srvs, boolean withNewCaches, ClusterState activationMode) throws Exception {
        assertActive(activationMode);

        Map<Integer, Integer> cacheData = startGridsAndLoadData(srvs, activationMode);

        stopAllGrids();

        for (int i = 0; i < srvs; i++) {
            if (withNewCaches)
                ccfgs = cacheConfigurations2();

            startGrid(i);
        }

        Ignite srv = ignite(0);

        checkNoCaches(srvs);

        srv.cluster().state(activationMode);

        final int CACHES = withNewCaches ? 4 : 2;

        for (int i = 0; i < srvs; i++)
            checkCachesOnNode(i, CACHES);

        DataStorageConfiguration dsCfg = srv.configuration().getDataStorageConfiguration();

        checkCachesData(cacheData, dsCfg);

        checkCaches(srvs, CACHES);

        int nodes = srvs;

        startGrid(nodes++, false);

        for (int i = 0; i < nodes; i++)
            checkCachesOnNode(i, CACHES);

        checkCaches(nodes, CACHES);

        startGrid(nodes++, true);

        checkCachesOnNode(nodes - 1, CACHES, false);

        checkCaches(nodes, CACHES);

        for (int i = 0; i < nodes; i++)
            checkCachesOnNode(i, CACHES);

        checkCachesData(cacheData, dsCfg);
    }