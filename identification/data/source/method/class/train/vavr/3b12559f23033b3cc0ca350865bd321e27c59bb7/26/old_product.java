static Stream<Long> range(long from, long toExclusive) {
        if (from >= toExclusive) {
            return Nil.instance();
        } else {
            return Stream.rangeClosed(from, toExclusive - 1);
        }
    }