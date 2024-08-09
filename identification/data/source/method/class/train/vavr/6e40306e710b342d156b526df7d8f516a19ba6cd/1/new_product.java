public static TreeSet<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return TreeSet.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }