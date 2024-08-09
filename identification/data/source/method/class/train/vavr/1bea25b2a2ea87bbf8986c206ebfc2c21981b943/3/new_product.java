public static Array<Integer> rangeBy(int from, int toExclusive, int step) {
        return Array.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }