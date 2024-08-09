public static Stream<Integer> rangeClosed(int from, int toInclusive) {
        return Stream.ofAll(Iterator.rangeClosed(from, toInclusive));
    }