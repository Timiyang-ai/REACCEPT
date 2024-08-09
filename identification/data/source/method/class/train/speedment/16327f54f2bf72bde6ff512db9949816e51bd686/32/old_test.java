    @Test
    void filter() {
        final Map<String, Integer> expected = refStream().filter(KEY_STARTS_WITH_D).collect(TO_MAP);
        final Map<String, Integer> actual = instance.filter(KEY_STARTS_WITH_D).toMap();
        assertEquals(expected, actual);
    }