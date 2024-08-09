public static Vector<Long> range(long from, long toExclusive) {
        return Vector.ofAll(Iterator.range(from, toExclusive));
    }