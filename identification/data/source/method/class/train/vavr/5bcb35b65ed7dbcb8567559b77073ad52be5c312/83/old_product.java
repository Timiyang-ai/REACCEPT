public static Vector<Long> rangeBy(long from, long toExclusive, long step) {
        return Vector.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }