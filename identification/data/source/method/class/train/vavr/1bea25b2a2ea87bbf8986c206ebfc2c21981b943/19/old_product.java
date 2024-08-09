public static Array<Long> rangeBy(long from, long toExclusive, long step) {
        return Array.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }