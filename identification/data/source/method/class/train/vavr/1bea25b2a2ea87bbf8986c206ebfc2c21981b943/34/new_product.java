public static Array<Long> rangeClosed(long from, long toInclusive) {
        return Array.ofAll(Iterator.rangeClosed(from, toInclusive));
    }