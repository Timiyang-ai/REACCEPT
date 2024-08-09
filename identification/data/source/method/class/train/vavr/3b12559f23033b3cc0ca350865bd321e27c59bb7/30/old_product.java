static Stream<Integer> rangeClosed(int from, int toInclusive) {
        if (from > toInclusive) {
            return Nil.instance();
        } else if (from == Integer.MIN_VALUE && toInclusive == Integer.MIN_VALUE) {
            return new Cons<>(Integer.MIN_VALUE, Nil::instance);
        } else {
            return Stream.of(new Iterator<Integer>() {
                int i = from;

                @Override
                public boolean hasNext() {
                    return i <= toInclusive;
                }

                @Override
                public Integer next() {
                    return i++;
                }
            });
        }
    }