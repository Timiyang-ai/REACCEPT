    @Test
    void mapToLong() {
        final long expected = refStream().mapToLong(Map.Entry::getValue).map(i -> i + 1).sum();
        final long actual = instance.mapToLong(e -> e.getValue() + 1).sum();
        assertEquals(expected, actual);
    }