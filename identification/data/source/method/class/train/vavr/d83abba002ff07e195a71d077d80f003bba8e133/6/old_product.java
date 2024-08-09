static Stream<Integer> rangeBy(int from, int toExclusive, int step) {
        if (step == 0) {
            throw new IllegalArgumentException("step cannot be 0.");
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