public static Array<Integer> rangeClosed(int from, int toInclusive) {
        return Array.ofAll(Iterator.rangeClosed(from, toInclusive));
    }