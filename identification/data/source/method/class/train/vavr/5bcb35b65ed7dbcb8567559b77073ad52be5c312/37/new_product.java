public static Vector<Integer> rangeBy(int from, int toExclusive, int step) {
        return ofAll(ArrayType.<int[]> asPrimitives(int.class, Iterator.rangeBy(from, toExclusive, step)));
    }