@Test
    public void testClearStorage() throws Exception {
        GraphOfTheGodsFactory.load(graph);
        tearDown();
        config.set(ConfigElement.getPath(GraphDatabaseConfiguration.DROP_ON_CLEAR), true);
        final Backend backend = getBackend(config, false);
        assertStorageExists(backend, true);
        clearGraph(config);
        try { backend.close(); } catch (Exception e) { /* Most backends do not support closing after clearing */}
        try (final Backend newBackend = getBackend(config, false)) {
            assertStorageExists(newBackend, false);
        }
    }