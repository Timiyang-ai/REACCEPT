static List<Long> rangeClosed(long from, long toInclusive) {
        return List.ofAll(Iterator.rangeClosed(from, toInclusive));
    }