@Test
    public void testSetState() throws Exception {
        final String partCacheName = "part_cache";
        final String replCacheName = "repl_cache";
        Ignite ignite = startGrids(2);

        ignite.cluster().state(ACTIVE);

        ignite.createCache(ClusterStateTestUtils.partitionedCache(partCacheName));
        ignite.createCache(ClusterStateTestUtils.replicatedCache(replCacheName));

        ignite.cluster().state(INACTIVE);

        injectTestSystemOut();

        assertEquals(INACTIVE, ignite.cluster().state());

        // INACTIVE -> INACTIVE.
        setState(ignite, INACTIVE, "INACTIVE", partCacheName, replCacheName);

        // INACTIVE -> READ_ONLY.
        setState(ignite, READ_ONLY, "READ_ONLY", partCacheName, replCacheName);

        // READ_ONLY -> READ_ONLY.
        setState(ignite, READ_ONLY, "READ_ONLY", partCacheName, replCacheName);

        // READ_ONLY -> ACTIVE.
        setState(ignite, ACTIVE, "ACTIVE", partCacheName, replCacheName);

        // ACTIVE -> ACTIVE.
        setState(ignite, ACTIVE, "ACTIVE", partCacheName, replCacheName);

        // ACTIVE -> INACTIVE.
        setState(ignite, INACTIVE, "INACTIVE", partCacheName, replCacheName);

        // INACTIVE -> ACTIVE.
        setState(ignite, ACTIVE, "ACTIVE", partCacheName, replCacheName);

        // ACTIVE -> READ_ONLY.
        setState(ignite, READ_ONLY, "READ_ONLY", partCacheName, replCacheName);

        // READ_ONLY -> INACTIVE.
        setState(ignite, INACTIVE, "INACTIVE", partCacheName, replCacheName);
    }