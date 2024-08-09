public static Vector<Integer> range(int from, int toExclusive) {
        return ofAll(ArrayType.<int[]> asPrimitives(int.class, Iterator.range(from, toExclusive)));
    }