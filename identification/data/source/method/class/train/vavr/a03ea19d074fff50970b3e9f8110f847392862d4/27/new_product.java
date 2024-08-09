public static BitSet<Integer> range(int from, int toExclusive) {
        return BitSet.ofAll(Iterator.range(from, toExclusive));
    }