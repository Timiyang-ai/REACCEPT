public static Queue<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return Queue.ofAll(List.rangeClosedBy(from, toInclusive, step));
    }