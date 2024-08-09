static Stream<Integer> rangeClosed(int from, int toInclusive) {
        if (from > toInclusive) {
            return Nil.instance();
        } else if (from == Integer.MAX_VALUE) {
            return new Cons<>(Integer.MAX_VALUE, Nil::instance);
        } else {
            return new Cons<>(from, () -> rangeClosed(from + 1, toInclusive));
        }
    }