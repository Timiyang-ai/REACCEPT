static Stream<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0");
        }
        if (step > 0) {
            if (from > toInclusive) {
                return Nil.instance();
            } else if (from > Integer.MAX_VALUE - step) {
                return Stream.of(from);
            } else {
                return new Cons<>(() -> from, () -> rangeClosedBy(from + step, toInclusive, step));
            }
        } else {
            if (from < toInclusive) {
                return Nil.instance();
            } else if (from < Integer.MIN_VALUE - step) {
                return Stream.of(from);
            } else {
                return new Cons<>(() -> from, () -> rangeClosedBy(from + step, toInclusive, step));
            }
        }
    }