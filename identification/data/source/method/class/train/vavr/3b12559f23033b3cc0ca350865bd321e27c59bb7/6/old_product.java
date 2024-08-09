static Stream<Long> rangeClosed(long from, long toInclusive) {
        if (from > toInclusive) {
            return Nil.instance();
        } else if (from == Long.MAX_VALUE) {
            return Stream.of(Long.MAX_VALUE);
        } else {
            return new Cons<>(() -> from, () -> rangeClosed(from + 1, toInclusive));
        }
    }