    @Test
    void toSortedMap() {
        final Map<String, Integer> expected = refStream().collect(TO_MAP);
        final Map<String, Integer> actual = instance.toSortedMap();
        assertEquals(expected, actual);
        assertTrue(actual instanceof NavigableMap);
    }