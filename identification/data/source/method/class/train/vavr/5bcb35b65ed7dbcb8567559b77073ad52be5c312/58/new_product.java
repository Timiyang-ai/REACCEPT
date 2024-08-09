public static Vector<Long> rangeBy(long from, long toExclusive, long step) {
        return ofAll(ArrayType.<long[]> asPrimitives(long.class, Iterator.rangeBy(from, toExclusive, step)));
    }