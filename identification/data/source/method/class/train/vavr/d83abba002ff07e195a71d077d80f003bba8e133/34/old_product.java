static Stream<Integer> rangeBy(int from, int toExclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("Step should not be equal to zero");
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