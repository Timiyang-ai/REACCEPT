static Stream<Integer> range(int from, int toExclusive) {
        if (toExclusive == Integer.MIN_VALUE) {
            return Nil.instance();
        } else {
            return Stream.rangeClosed(from, toExclusive - 1);
        }
    }