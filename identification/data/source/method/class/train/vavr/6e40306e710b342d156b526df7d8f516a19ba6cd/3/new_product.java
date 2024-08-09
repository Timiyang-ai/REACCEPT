public static TreeSet<Long> rangeBy(long from, long toExclusive, long step) {
        return TreeSet.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }