static List<Long> range(long from, long toExclusive) {
        if (from >= toExclusive) {
            return Nil.instance();
        } else {
            return List.rangeClosed(from, toExclusive - 1);
        }
    }