static List<Integer> range(int from, int toExclusive) {
        if (toExclusive == Integer.MIN_VALUE) {
            return Nil.instance();
        } else {
            return List.rangeClosed(from, toExclusive - 1);
        }
    }