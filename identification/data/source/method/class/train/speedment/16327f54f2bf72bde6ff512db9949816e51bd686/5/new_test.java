    @Test
    void mapToInt() {
        final int expected = refStream().mapToInt(Map.Entry::getValue).map(i -> i + 1).sum();
        final int actual = instance.mapToInt(e -> e.getValue() + 1).sum();
        assertEquals(expected, actual);
    }