@Test
    public void testInitContext() {
        ClusterNode node = grid(1).cluster().localNode();

        String upstreamCacheName = "CACHE_1_" + UUID.randomUUID();
        String datasetCacheName = "CACHE_2_" + UUID.randomUUID();

        CacheConfiguration<Integer, Integer> upstreamCacheConfiguration = new CacheConfiguration<>();
        upstreamCacheConfiguration.setName(upstreamCacheName);
        upstreamCacheConfiguration.setAffinity(new TestAffinityFunction(node));
        IgniteCache<Integer, Integer> upstreamCache = ignite.createCache(upstreamCacheConfiguration);

        CacheConfiguration<Integer, Integer> datasetCacheConfiguration = new CacheConfiguration<>();
        datasetCacheConfiguration.setName(datasetCacheName);
        datasetCacheConfiguration.setAffinity(new TestAffinityFunction(node));
        IgniteCache<Integer, Integer> datasetCache = ignite.createCache(datasetCacheConfiguration);

        upstreamCache.put(42, 42);

        ComputeUtils.<Integer, Integer, Integer>initContext(
            ignite,
            upstreamCacheName,
            (k, v) -> true,
            datasetCacheName,
            (upstream, upstreamSize) -> {

                assertEquals(1, upstreamSize);

                UpstreamEntry<Integer, Integer> e = upstream.next();
                return e.getKey() + e.getValue();
            },
            0
        );

        assertEquals(1, datasetCache.size());
        assertEquals(84, datasetCache.get(0).intValue());
    }