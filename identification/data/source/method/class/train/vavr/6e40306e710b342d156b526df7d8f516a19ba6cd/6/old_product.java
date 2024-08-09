public static TreeSet<Long> range(long from, long toExclusive) {
        return TreeSet.rangeBy(from, toExclusive, 1);
    }