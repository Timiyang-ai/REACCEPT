static Stream<Integer> range(int from, int toExclusive) {
        return rangeBy(from, toExclusive, 1);
    }