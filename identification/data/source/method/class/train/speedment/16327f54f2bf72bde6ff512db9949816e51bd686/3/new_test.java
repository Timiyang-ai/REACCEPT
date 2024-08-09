    @Test
    void allMatch() {
        final Predicate<Map.Entry<String, Integer>> predicate = e -> !e.getKey().isEmpty();
        final boolean expected = refStream().allMatch(predicate);
        final boolean actual = instance.allMatch(predicate);
        assertEquals(expected, actual);
    }