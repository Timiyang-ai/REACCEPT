@Test
    public void testCacheConfiguration() throws Exception {
        try (Ignite ignored = Ignition.start(Config.getServerConfiguration());
             IgniteClient client = Ignition.startClient(getClientConfiguration())
        ) {
            final String CACHE_NAME = "testCacheConfiguration";

            ClientCacheConfiguration cacheCfg = new ClientCacheConfiguration().setName(CACHE_NAME)
                .setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL)
                .setBackups(3)
                .setCacheMode(CacheMode.PARTITIONED)
                .setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC)
                .setEagerTtl(false)
                .setGroupName("FunctionalTest")
                .setDefaultLockTimeout(12345)
                .setPartitionLossPolicy(PartitionLossPolicy.READ_WRITE_ALL)
                .setReadFromBackup(true)
                .setRebalanceBatchSize(67890)
                .setRebalanceBatchesPrefetchCount(102938)
                .setRebalanceDelay(54321)
                .setRebalanceMode(CacheRebalanceMode.SYNC)
                .setRebalanceOrder(2)
                .setRebalanceThrottle(564738)
                .setRebalanceTimeout(142536)
                .setKeyConfiguration(new CacheKeyConfiguration("Employee", "orgId"))
                .setQueryEntities(new QueryEntity(int.class.getName(), "Employee")
                    .setTableName("EMPLOYEE")
                    .setFields(
                        Stream.of(
                            new SimpleEntry<>("id", Integer.class.getName()),
                            new SimpleEntry<>("orgId", Integer.class.getName())
                        ).collect(Collectors.toMap(
                            SimpleEntry::getKey, SimpleEntry::getValue, (a, b) -> a, LinkedHashMap::new
                        ))
                    )
                    // During query normalization null keyFields become empty set.
                    // Set empty collection for comparator.
                    .setKeyFields(Collections.emptySet())
                    .setKeyFieldName("id")
                    .setNotNullFields(Collections.singleton("id"))
                    .setDefaultFieldValues(Collections.singletonMap("id", 0))
                    .setIndexes(Collections.singletonList(new QueryIndex("id", true, "IDX_EMPLOYEE_ID")))
                    .setAliases(Stream.of("id", "orgId").collect(Collectors.toMap(f -> f, String::toUpperCase)))
                )
                .setExpiryPolicy(new PlatformExpiryPolicy(10, 20, 30));

            ClientCache cache = client.createCache(cacheCfg);

            assertEquals(CACHE_NAME, cache.getName());

            assertTrue(Comparers.equal(cacheCfg, cache.getConfiguration()));
        }
    }