    @Test
    void noneMatch() {
        final Predicate<Map.Entry<String, Integer>> predicate = e -> e.getKey().isEmpty();
        final boolean expected = refStream().noneMatch(predicate);
        final boolean actual = instance.noneMatch(predicate);
        assertEquals(expected, actual);
    }