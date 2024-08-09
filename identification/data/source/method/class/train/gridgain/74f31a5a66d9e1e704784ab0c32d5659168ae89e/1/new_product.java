private void setState(Ignite ignite, ClusterState state, String strState, String... cacheNames) {
        assertEquals(EXIT_CODE_OK, execute("--set-state", strState));

        assertEquals(state, ignite.cluster().state());

        assertContains(log, testOut.toString(), "Cluster state changed to " + strState);

        List<IgniteEx> nodes = IntStream.range(0, 2)
            .mapToObj(this::grid)
            .collect(Collectors.toList());

        ClusterStateTestUtils.putSomeDataAndCheck(log, nodes, cacheNames);

        if (state == ACTIVE) {
            for (String cacheName : cacheNames)
                grid(0).cache(cacheName).clear();
        }
    }