public static TreeSet<Long> rangeClosed(long from, long toInclusive) {
        return TreeSet.ofAll(Iterator.rangeClosed(from, toInclusive));
    }