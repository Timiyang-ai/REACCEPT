static BitSet rangeClosedBy(int from, int toInclusive, int step) {
        return BitSet.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }