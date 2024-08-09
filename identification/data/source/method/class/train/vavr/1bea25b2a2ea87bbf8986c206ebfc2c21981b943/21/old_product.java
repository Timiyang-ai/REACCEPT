public static Queue<Long> rangeBy(long from, long toExclusive, long step) {
        return Queue.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }