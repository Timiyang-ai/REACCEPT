static Stream<Long> rangeClosedBy(long from, long toInclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step is zero");
        }
        if (step > 0) {
            if (from > toInclusive) {
                return Nil.instance();
            } else if (from > Long.MAX_VALUE - step) {
                return Stream.of(from);
            } else {
                return new Cons<>(() -> from, () -> rangeClosedBy(from + step, toInclusive, step));
            }
        } else {
            if (from < toInclusive) {
                return Nil.instance();
            } else if (from < Long.MIN_VALUE - step) {
                return Stream.of(from);
            } else {
                return new Cons<>(() -> from, () -> rangeClosedBy(from + step, toInclusive, step));
            }
        }
    }