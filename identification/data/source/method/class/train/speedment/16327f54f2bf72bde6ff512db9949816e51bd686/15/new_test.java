    @Test
    void filterKey() {
        final Map<String, Integer> expected = refStream().filter(KEY_STARTS_WITH_D).collect(TO_MAP);
        final Map<String, Integer> actual = instance.filterKey(k -> k.startsWith("d")).toMap();
        assertEquals(expected, actual);
    }