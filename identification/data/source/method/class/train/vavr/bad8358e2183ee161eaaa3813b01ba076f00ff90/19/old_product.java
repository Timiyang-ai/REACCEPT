static List<Integer> rangeClosedBy(int from, int toInclusive, int step) {
        return List.ofAll(Iterator.rangeClosedBy(from, toInclusive, step));
    }