public static Queue<Integer> rangeBy(int from, int toExclusive, int step) {
        return Queue.ofAll(List.rangeBy(from, toExclusive, step));
    }