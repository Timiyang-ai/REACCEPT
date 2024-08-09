    @Test
    void flatMapToDouble() {
        final double expected = refStream().flatMapToDouble(e -> LongStream.range(0, e.getValue()).mapToDouble(l -> l)).sum();
        final double actual = instance.flatMapToDouble(e -> LongStream.range(0, e.getValue()).mapToDouble(l -> l)).sum();
        assertEquals(expected, actual, EPSILON);
    }