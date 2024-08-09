static Stream<Long> rangeClosed(long from, long toInclusive) {
        return Stream.ofAll(Iterator.rangeClosed(from, toInclusive));
    }