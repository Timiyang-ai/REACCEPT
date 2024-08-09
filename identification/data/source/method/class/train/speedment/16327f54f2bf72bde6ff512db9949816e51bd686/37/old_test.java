    @Test
    void anyMatch() {
        final Predicate<Map.Entry<String, Integer>> predicate = e -> e.getKey().contains("a");
        final boolean expected = refStream().anyMatch(predicate);
        final boolean actual = instance.anyMatch(predicate);
        assertEquals(expected, actual);
    }