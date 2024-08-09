public static Queue<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return Queue.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }