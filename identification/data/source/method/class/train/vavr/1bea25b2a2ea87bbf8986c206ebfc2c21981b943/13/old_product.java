public static Queue<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return Queue.ofAll(List.rangeClosedBy(from, toInclusive, step));
    }