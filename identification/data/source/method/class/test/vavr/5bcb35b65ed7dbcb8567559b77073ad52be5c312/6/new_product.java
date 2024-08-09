public static Vector<Integer> rangeClosed(int from, int toInclusive) {
        return ofAll(ArrayType.<int[]> asPrimitives(int.class, Iterator.rangeClosed(from, toInclusive)));
    }