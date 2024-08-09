@Test
    public void loadCacheTest() {
        Ignition.stopAll(true);

        LOGGER.info("Running loadCache test");

        LOGGER.info("Filling Cassandra table with test data");

        CacheStore store = CacheStoreHelper.createCacheStore("personTypes",
            new ClassPathResource("org/apache/ignite/tests/persistence/pojo/persistence-settings-3.xml"),
            CassandraHelper.getAdminDataSrc());

        Collection<CacheEntryImpl<PersonId, Person>> entries = TestsHelper.generatePersonIdsPersonsEntries();

        //noinspection unchecked
        store.writeAll(entries);

        LOGGER.info("Cassandra table filled with test data");

        LOGGER.info("Running loadCache test");

        try (Ignite ignite = Ignition.start("org/apache/ignite/tests/persistence/pojo/ignite-config.xml")) {
            CacheConfiguration<PersonId, Person> ccfg = new CacheConfiguration<>("cache3");

            IgniteCache<PersonId, Person> personCache3 = ignite.getOrCreateCache(ccfg);

            int size = personCache3.size(CachePeekMode.ALL);

            LOGGER.info("Initial cache size " + size);

            LOGGER.info("Loading cache data from Cassandra table");

            String qry = "select * from test1.pojo_test3 limit 3";

            personCache3.loadCache(null, qry);

            size = personCache3.size(CachePeekMode.ALL);
            Assert.assertEquals("Cache data was incorrectly loaded from Cassandra table by '" + qry + "'", 3, size);

            personCache3.clear();

            personCache3.loadCache(null, new SimpleStatement(qry));

            size = personCache3.size(CachePeekMode.ALL);
            Assert.assertEquals("Cache data was incorrectly loaded from Cassandra table by statement", 3, size);

            personCache3.clear();

            personCache3.loadCache(null);

            size = personCache3.size(CachePeekMode.ALL);
            Assert.assertEquals("Cache data was incorrectly loaded from Cassandra. " +
                    "Expected number of records is " + TestsHelper.getBulkOperationSize() +
                    ", but loaded number of records is " + size,
                TestsHelper.getBulkOperationSize(), size);

            LOGGER.info("Cache data loaded from Cassandra table");
        }

        LOGGER.info("loadCache test passed");
    }