public static Queue<Integer> range(int from, int toExclusive) {
        return Queue.ofAll(Iterator.range(from, toExclusive));
    }