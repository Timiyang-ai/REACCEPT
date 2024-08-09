public static Queue<Long> range(long from, long toExclusive) {
        return Queue.ofAll(Iterator.range(from, toExclusive));
    }