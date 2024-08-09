    @Test
    void toConcurrentMap() {
        final Map<String, Integer> expected = refStream().collect(TO_MAP);
        final Map<String, Integer> actual = instance.toConcurrentMap();
        assertEquals(expected, actual);
        assertTrue(actual instanceof ConcurrentMap);
    }