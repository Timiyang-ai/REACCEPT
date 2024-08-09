    @Test
    void maxByValue() {
        final Optional<Map.Entry<String, Integer>> expected = refStream().max(Map.Entry.comparingByValue());
        final Optional<Map.Entry<String, Integer>> actual = instance.maxByValue(Comparator.naturalOrder());
        assertEquals(expected, actual);
    }