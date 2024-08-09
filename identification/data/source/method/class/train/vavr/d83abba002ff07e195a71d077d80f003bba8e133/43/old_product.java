static Stream<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return Stream.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }