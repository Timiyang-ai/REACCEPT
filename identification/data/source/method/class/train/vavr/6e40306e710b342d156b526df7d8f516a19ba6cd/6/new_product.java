public static TreeSet<Long> range(long from, long toExclusive) {
        return TreeSet.ofAll(Iterator.range(from, toExclusive));
    }