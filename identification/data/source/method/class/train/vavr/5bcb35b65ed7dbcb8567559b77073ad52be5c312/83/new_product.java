public static Vector<Long> rangeBy(long from, long toExclusive, long step) {
        return ofAll(Iterator.rangeBy(from, toExclusive, step));
    }