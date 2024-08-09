public static Vector<Long> rangeClosed(long from, long toInclusive) {
        return ofAll(ArrayType.<long[]> asPrimitives(long.class, Iterator.rangeClosed(from, toInclusive)));
    }