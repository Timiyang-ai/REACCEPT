static Stream<Long> range(long from, long toExclusive) {
        return rangeBy(from, toExclusive, 1);
    }