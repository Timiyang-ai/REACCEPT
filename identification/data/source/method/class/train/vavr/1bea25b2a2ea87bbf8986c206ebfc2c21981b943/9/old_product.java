public static Queue<Long> rangeClosed(long from, long toInclusive) {
        return Queue.ofAll(List.rangeClosed(from, toInclusive));
    }