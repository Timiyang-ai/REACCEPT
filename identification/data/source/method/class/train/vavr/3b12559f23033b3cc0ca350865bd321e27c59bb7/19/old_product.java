static List<Integer> rangeClosed(int from, int toInclusive) {
        return List.ofAll(Iterator.rangeClosed(from, toInclusive));
    }