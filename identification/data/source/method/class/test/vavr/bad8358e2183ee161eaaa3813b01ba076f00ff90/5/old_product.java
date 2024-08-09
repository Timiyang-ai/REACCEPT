static List<Long> rangeBy(long from, long toExclusive, long step) {
        return List.ofAll(Iterator.rangeBy(from, toExclusive, step));
    }