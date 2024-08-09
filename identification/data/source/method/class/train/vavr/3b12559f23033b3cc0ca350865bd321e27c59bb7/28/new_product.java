public static Stream<Integer> range(int from, int toExclusive) {
        return Stream.ofAll(Iterator.range(from, toExclusive));
    }