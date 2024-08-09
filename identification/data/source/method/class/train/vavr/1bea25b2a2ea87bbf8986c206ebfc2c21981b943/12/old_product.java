static Array<Long> range(long from, long toExclusive) {
        return Array.ofAll(Iterator.range(from, toExclusive));
    }