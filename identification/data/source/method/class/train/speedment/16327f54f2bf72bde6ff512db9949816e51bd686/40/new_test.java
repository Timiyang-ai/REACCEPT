    @Test
    void mapToDouble() {
        final double expected = refStream().mapToDouble(Map.Entry::getValue).map(i -> i + 1).sum();
        final double actual = instance.mapToDouble(e -> e.getValue() + 1).sum();
        assertEquals(expected, actual);
    }