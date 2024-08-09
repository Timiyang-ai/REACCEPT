public static Queue<Long> range(long from, long toExclusive) {
        return Queue.ofAll(List.range(from, toExclusive));
    }