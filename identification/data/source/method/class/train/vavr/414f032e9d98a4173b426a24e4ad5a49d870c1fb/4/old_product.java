public static Vector<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return Vector.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }