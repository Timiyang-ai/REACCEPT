public static Comparator chainedComparator(Comparator comparator1, Comparator comparator2) {
        return chainedComparator(new Comparator[] {comparator1, comparator2});
    }