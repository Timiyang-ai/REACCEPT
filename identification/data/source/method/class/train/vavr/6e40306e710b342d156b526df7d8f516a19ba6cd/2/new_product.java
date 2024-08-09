public static TreeSet<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return TreeSet.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }