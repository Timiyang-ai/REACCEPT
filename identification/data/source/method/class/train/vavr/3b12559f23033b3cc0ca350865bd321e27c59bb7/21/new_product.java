public static Stream<Long> range(long from, long toExclusive) {
        return Stream.ofAll(Iterator.range(from, toExclusive));
    }