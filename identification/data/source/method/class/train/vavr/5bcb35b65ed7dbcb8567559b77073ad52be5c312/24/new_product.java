public static Vector<Integer> range(int from, int toExclusive) {
        return ofAll(Iterator.range(from, toExclusive));
    }