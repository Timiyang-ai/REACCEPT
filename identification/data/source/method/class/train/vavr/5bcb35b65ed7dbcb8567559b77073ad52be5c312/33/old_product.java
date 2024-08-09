public static Vector<Integer> rangeBy(int from, int toExclusive, int step) {
        return Vector.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }