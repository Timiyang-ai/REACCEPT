static BitSet rangeBy(int from, int toExclusive, int step) {
        return BitSet.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }