public static Queue<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return Queue.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }