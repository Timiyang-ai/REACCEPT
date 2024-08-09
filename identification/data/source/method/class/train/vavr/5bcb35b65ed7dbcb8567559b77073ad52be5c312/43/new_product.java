public static Vector<Integer> rangeClosed(int from, int toInclusive) {
        return Vector.ofAll(Iterator.rangeClosed(from, toInclusive));
    }