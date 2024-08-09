static Stream<Integer> rangeClosed(int from, int toInclusive) {
        return rangeClosedBy(from, toInclusive, 1);
    }