public static Array<Long> range(long from, long toExclusive) {
        return ofAll(Iterator.range(from, toExclusive));
    }