public static Queue<Long> rangeClosed(long from, long toInclusive) {
        return Queue.ofAll(Iterator.rangeClosed(from, toInclusive));
    }