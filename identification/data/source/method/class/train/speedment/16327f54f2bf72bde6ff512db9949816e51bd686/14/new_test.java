    @Test
    void flatMapToInt() {
        final int expected = refStream().flatMapToInt(e -> IntStream.range(0, e.getValue())).sum();
        final int actual = instance.flatMapToInt(e -> IntStream.range(0, e.getValue())).sum();
        assertEquals(expected, actual);
    }