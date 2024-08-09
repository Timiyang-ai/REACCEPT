public static TreeSet<Integer> rangeClosed(int from, int toInclusive) {
        return TreeSet.ofAll(Iterator.rangeClosed(from, toInclusive));
    }