static Stream<Integer> rangeClosed(int from, int toInclusive) {
        if (from > toInclusive) {
            return Nil.instance();
        } else if (from == Integer.MAX_VALUE) {
            return Stream.of(Integer.MAX_VALUE);
        } else {
            return new Cons<>(from, () -> rangeClosed(from + 1, toInclusive));
        }
    }