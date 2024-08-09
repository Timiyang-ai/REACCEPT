static List<Integer> range(int from, int toExclusive) {
        return List.ofAll(Iterator.range(from, toExclusive));
    }