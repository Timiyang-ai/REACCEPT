@Test
    public void testGetData() {
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
        datasetCache.put(0, 0);

        UUID datasetId = UUID.randomUUID();

        IgniteAtomicLong cnt = ignite.atomicLong("CNT_" + datasetId, 0, true);

        for (int i = 0; i < 10; i++) {
            Collection<TestPartitionData> data = ComputeUtils.affinityCallWithRetries(
                ignite,
                Arrays.asList(datasetCacheName, upstreamCacheName),
                part -> ComputeUtils.<Integer, Integer, Serializable, TestPartitionData>getData(
                    ignite,
                    upstreamCacheName,
                    (k, v) -> true,
                    UpstreamTransformerBuilder.identity(),
                    datasetCacheName,
                    datasetId,
                    (env, upstream, upstreamSize, ctx) -> {
                        cnt.incrementAndGet();

                        assertEquals(1, upstreamSize);

                        UpstreamEntry<Integer, Integer> e = upstream.next();
                        return new TestPartitionData(e.getKey() + e.getValue());
                    },
                    TestUtils.testEnvBuilder().buildForWorker(part)
                ),
                0
            );

            assertEquals(1, data.size());

            TestPartitionData dataElement = data.iterator().next();
            assertEquals(84, dataElement.val.intValue());
        }

        assertEquals(1, cnt.get());
    }