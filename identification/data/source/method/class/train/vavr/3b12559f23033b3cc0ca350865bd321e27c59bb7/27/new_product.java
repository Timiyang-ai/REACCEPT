static List<Integer> range(int from, int toExclusive) {
        if (from >= toExclusive) {
            return Nil.instance();
        } else {
            return List.rangeClosed(from, toExclusive - 1);
        }
    }