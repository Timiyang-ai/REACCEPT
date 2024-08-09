    @Test
    void toConcurrentNavigableMap() {
        final Map<String, Integer> expected = refStream().collect(TO_MAP);
        final Map<String, Integer> actual = instance.toConcurrentNavigableMap();
        assertEquals(expected, actual);
    }