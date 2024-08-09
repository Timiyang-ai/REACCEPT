public static Stream<Long> rangeBy(long from, long toExclusive, long step) {
        return Stream.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }