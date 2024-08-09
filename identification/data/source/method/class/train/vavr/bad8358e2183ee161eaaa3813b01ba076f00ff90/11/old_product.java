static List<Integer> rangeBy(int from, int toExclusive, int step) {
        return List.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }