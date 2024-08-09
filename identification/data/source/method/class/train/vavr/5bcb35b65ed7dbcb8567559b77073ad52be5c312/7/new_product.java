public static Vector<Long> rangeClosed(long from, long toInclusive) {
        return Vector.ofAll(Iterator.rangeClosed(from, toInclusive));
    }