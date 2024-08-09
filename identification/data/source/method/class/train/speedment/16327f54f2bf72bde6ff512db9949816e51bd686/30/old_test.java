    @Test
    void flatMapToLong() {
        final long expected = refStream().flatMapToLong(e -> LongStream.range(0, e.getValue())).sum();
        final long actual = instance.flatMapToLong(e -> LongStream.range(0, e.getValue())).sum();
        assertEquals(expected, actual);
    }