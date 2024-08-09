    @Test
    void toMap() {
        final Map<String, Integer> expected = refStream().collect(TO_MAP);
        final Map<String, Integer> actual = instance.toMap();
        assertEquals(expected, actual);
    }