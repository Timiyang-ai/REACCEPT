public static Array<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return Array.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }