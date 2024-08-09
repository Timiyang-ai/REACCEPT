    @Test
    void min() {
        final Optional<Map.Entry<String, Integer>> expected = refStream().min(Map.Entry.comparingByValue());
        final Optional<Map.Entry<String, Integer>> actual = instance.min(Map.Entry.comparingByValue());
        assertEquals(expected, actual);
    }