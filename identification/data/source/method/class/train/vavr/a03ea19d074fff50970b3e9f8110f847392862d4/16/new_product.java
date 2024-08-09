public static BitSet<Integer> rangeClosed(int from, int toInclusive) {
        return BitSet.ofAll(Iterator.rangeClosed(from, toInclusive));
    }