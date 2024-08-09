    @Test
    void max() {
        final Optional<Map.Entry<String, Integer>> expected = refStream().max(Map.Entry.comparingByValue());
        final Optional<Map.Entry<String, Integer>> actual = instance.max(Map.Entry.comparingByValue());
        assertEquals(expected, actual);
    }