public static Vector<Long> range(long from, long toExclusive) {
        return ofAll(ArrayType.<long[]> asPrimitives(long.class, Iterator.range(from, toExclusive)));
    }