    @Test
    void throwingMerger() {
        final BinaryOperator<Integer> merger = MapStream.throwingMerger();
        assertThrows(IllegalStateException.class, () -> merger.apply(42, 42));
    }