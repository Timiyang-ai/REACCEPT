    @Test
    void maxByKey() {
        final Optional<Map.Entry<String, Integer>> expected = refStream().max(Map.Entry.comparingByKey());
        final Optional<Map.Entry<String, Integer>> actual = instance.maxByKey(Comparator.naturalOrder());
        assertEquals(expected, actual);
    }