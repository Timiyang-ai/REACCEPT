public static TreeSet<Integer> rangeBy(int from, int toExclusive, int step) {
        return TreeSet.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }