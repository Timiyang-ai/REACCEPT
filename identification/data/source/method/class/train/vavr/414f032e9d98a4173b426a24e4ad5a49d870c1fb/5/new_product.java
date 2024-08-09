public static Vector<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return Vector.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }