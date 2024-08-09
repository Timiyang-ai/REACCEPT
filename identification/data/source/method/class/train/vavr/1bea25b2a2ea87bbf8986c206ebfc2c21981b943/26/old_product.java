public static Queue<Integer> range(int from, int toExclusive) {
        return Queue.ofAll(List.range(from, toExclusive));
    }