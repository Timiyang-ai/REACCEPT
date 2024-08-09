static Stream<Long> rangeClosed(long from, long toInclusive) {
        return rangeClosedBy(from, toInclusive, 1);
    }