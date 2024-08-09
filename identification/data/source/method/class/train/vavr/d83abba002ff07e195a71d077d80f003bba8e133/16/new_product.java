public static Stream<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return Stream.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }