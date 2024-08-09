public static Queue<Long> rangeBy(long from, long toExclusive, long step) {
        return Queue.ofAll(List.rangeBy(from, toExclusive, step));
    }