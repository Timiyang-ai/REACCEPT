    @Test
    public void clearStorageTest() throws Exception {
        final String store = "vertex";
        initialize(store);
        final Multimap<String, Object> doc1 = getDocument("Hello world", 1001, 5.2, Geoshape.point(48.0, 0.0), Geoshape.polygon(Arrays.asList(new double[][]{{-0.1, 47.9}, {0.1, 47.9}, {0.1, 48.1}, {-0.1, 48.1}, {-0.1, 47.9}})), Arrays.asList("1", "2", "3"), Sets.newHashSet("1", "2"), Instant.ofEpochSecond(1),
            false);
        add(store, "doc1", doc1, true);
        clopen();
        assertTrue(index.exists());
        tearDown();
        setUp();
        assertFalse(index.exists());
    }