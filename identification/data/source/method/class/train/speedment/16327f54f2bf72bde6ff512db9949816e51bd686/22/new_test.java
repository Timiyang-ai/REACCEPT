    @Test
    void minByValue() {
        final Optional<Map.Entry<String, Integer>> expected = refStream().min(Map.Entry.comparingByValue());
        final Optional<Map.Entry<String, Integer>> actual = instance.minByValue(Comparator.naturalOrder());
        assertEquals(expected, actual);
    }