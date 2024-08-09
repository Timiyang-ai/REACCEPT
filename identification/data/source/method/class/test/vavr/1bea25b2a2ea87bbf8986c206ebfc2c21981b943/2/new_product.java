public static Queue<Long> range(long from, long toExclusive) {
        return ofAll(Iterator.range(from, toExclusive));
    }