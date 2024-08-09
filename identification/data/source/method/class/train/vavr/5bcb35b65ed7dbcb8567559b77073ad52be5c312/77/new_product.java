public static Vector<Integer> range(int from, int toExclusive) {
        return Vector.ofAll(Iterator.range(from, toExclusive));
    }