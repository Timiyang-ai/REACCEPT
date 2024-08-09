@Test
    public void loadCacheTest() {
        Ignition.stopAll(true);

        LOGGER.info("Running loadCache test");

        LOGGER.info("Filling Cassandra table with test data");

        CacheStore store = CacheStoreHelper.createCacheStore("personTypes",
            new ClassPathResource("org/apache/ignite/tests/persistence/pojo/persistence-settings-3.xml"),
            CassandraHelper.getAdminDataSrc());

        Collection<CacheEntryImpl<PersonId, Person>> entries = TestsHelper.generatePersonIdsPersonsEntries();

        store.writeAll(entries);

        LOGGER.info("Cassandra table filled with test data");

        LOGGER.info("Running loadCache test");

        try (Ignite ignite = Ignition.start("org/apache/ignite/tests/persistence/pojo/ignite-config.xml")) {
            IgniteCache<PersonId, Person> personCache3 = ignite.getOrCreateCache(new CacheConfiguration<PersonId, Person>("cache3"));
            int size = personCache3.size(CachePeekMode.ALL);

            LOGGER.info("Initial cache size " + size);

            LOGGER.info("Loading cache data from Cassandra table");

            personCache3.loadCache(null, new String[] {"select * from test1.pojo_test3 limit 3"});

            size = personCache3.size(CachePeekMode.ALL);
            if (size != 3) {
                throw new RuntimeException("Cache data was incorrectly loaded from Cassandra. " +
                    "Expected number of records is 3, but loaded number of records is " + size);
            }

            LOGGER.info("Cache data loaded from Cassandra table");
        }

        LOGGER.info("loadCache test passed");
    }