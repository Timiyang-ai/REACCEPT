public static Vector<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return ofAll(ArrayType.<int[]> asPrimitives(int.class, Iterator.rangeClosedBy(from, toInclusive, step)));
    }