public static TreeSet<Long> rangeClosed(long from, long toInclusive) {
        return TreeSet.rangeClosedBy(from, toInclusive, 1L);
    }