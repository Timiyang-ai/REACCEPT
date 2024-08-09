    @Test
    void filterValue() {
        final Map<String, Integer> expected = refStream().filter(KEY_STARTS_WITH_D).collect(TO_MAP);
        final Map<String, Integer> actual = instance.filterValue(k -> k.equals(2)).toMap();
        assertEquals(expected, actual);
    }