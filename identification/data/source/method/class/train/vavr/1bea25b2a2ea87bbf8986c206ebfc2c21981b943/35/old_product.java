public static Queue<Integer> rangeClosed(int from, int toInclusive) {
        return Queue.ofAll(List.rangeClosed(from, toInclusive));
    }