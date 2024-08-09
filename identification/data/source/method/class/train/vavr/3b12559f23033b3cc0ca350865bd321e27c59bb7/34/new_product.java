static Stream<Integer> range(int from, int toExclusive) {
        if (from >= toExclusive) {
            return Nil.instance();
        } else {
            return Stream.rangeClosed(from, toExclusive - 1);
        }
    }