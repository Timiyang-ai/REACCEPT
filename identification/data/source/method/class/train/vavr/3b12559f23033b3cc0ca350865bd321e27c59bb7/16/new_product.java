static List<Integer> range(int from, int toExclusive) {
        return ofAll(Iterator.range(from, toExclusive));
    }