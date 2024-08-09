static Stream<Long> rangeBy(long from, long toExclusive, long step) {
        if (step == 0) {
            throw new IllegalArgumentException("step is zero");
        }
        if (step > 0) {
            if (from >= toExclusive) {
                return Nil.instance();
            } else {
                return new Cons<>(() -> from, () -> rangeBy(from + step, toExclusive, step));
            }
        } else {
            if (from <= toExclusive) {
                return Nil.instance();
            } else {
                return new Cons<>(() -> from, () -> rangeBy(from + step, toExclusive, step));
            }
        }
    }