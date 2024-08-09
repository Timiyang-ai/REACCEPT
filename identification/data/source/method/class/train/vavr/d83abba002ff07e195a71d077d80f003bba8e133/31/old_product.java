static Stream<Integer> rangeBy(int from, int toExclusive, int step) {
        return Stream.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }