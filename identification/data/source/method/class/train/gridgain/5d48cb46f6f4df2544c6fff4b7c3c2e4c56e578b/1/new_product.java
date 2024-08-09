private void reactivateSimple(int srvs, int clients, int activateFrom, ClusterState state) throws Exception {
        activateSimple(srvs, clients, activateFrom, state);

        if (state == ACTIVE)
            rolloverSegmentAtLeastTwice(activateFrom);

        for (int i = 0; i < srvs + clients; i++)
            checkCachesOnNode(i, DEFAULT_CACHES_COUNT);

        ignite(activateFrom).cluster().state(INACTIVE);
        ignite(activateFrom).cluster().state(state);

        if (state == ACTIVE)
            rolloverSegmentAtLeastTwice(activateFrom);

        for (int i = 0; i < srvs + clients; i++)
            checkCachesOnNode(i, DEFAULT_CACHES_COUNT);
    }