static List<Long> rangeClosedBy(long from, long toInclusive, long step) {
        return List.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }