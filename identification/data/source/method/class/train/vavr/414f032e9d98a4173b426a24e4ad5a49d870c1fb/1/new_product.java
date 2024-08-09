public static Vector<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return ofAll(ArrayType.<long[]> asPrimitives(long.class, Iterator.rangeClosedBy(from, toInclusive, step)));
    }