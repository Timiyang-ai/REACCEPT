static List<Long> range(long from, long toExclusive) {
        return List.ofAll(Iterator.range(from, toExclusive));
    }