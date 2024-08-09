    @Test
    void minByKey() {
        final Optional<Map.Entry<String, Integer>> expected = refStream().min(Map.Entry.comparingByKey());
        final Optional<Map.Entry<String, Integer>> actual = instance.minByKey(Comparator.naturalOrder());
        assertEquals(expected, actual);
    }