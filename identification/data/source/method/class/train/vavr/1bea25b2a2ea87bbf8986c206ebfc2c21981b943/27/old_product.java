public static Queue<Integer> rangeBy(int from, int toExclusive, int step) {
        return Queue.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }