@Test
    public void testBuild() {
        IgniteCache<Integer, String> upstreamCache = createTestCache(100, 10);
        CacheBasedDatasetBuilder<Integer, String> builder = new CacheBasedDatasetBuilder<>(ignite, upstreamCache);

        CacheBasedDataset<Integer, String, Long, AutoCloseable> dataset = builder.build(
            TestUtils.testEnvBuilder(),
            (env, upstream, upstreamSize) -> upstreamSize,
            (env, upstream, upstreamSize, ctx) -> null
        );

        Affinity<Integer> upstreamAffinity = ignite.affinity(upstreamCache.getName());
        Affinity<Integer> datasetAffinity = ignite.affinity(dataset.getDatasetCache().getName());

        int upstreamPartitions = upstreamAffinity.partitions();
        int datasetPartitions = datasetAffinity.partitions();

        assertEquals(upstreamPartitions, datasetPartitions);

        for (int part = 0; part < upstreamPartitions; part++) {
            Collection<ClusterNode> upstreamPartNodes = upstreamAffinity.mapPartitionToPrimaryAndBackups(part);
            Collection<ClusterNode> datasetPartNodes = datasetAffinity.mapPartitionToPrimaryAndBackups(part);

            assertEqualsCollections(upstreamPartNodes, datasetPartNodes);
        }
    }