public static Queue<Integer> rangeClosed(int from, int toInclusive) {
        return Queue.ofAll(Iterator.rangeClosed(from, toInclusive));
    }