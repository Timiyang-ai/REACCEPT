public static TreeSet<Integer> range(int from, int toExclusive) {
        return TreeSet.ofAll(Iterator.range(from, toExclusive));
    }