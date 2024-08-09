public static Array<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return Array.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }