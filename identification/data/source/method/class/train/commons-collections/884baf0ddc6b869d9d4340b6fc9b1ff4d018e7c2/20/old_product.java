public static Comparator chainedComparator(Collection comparators) {
        return chainedComparator(
            (Comparator[]) comparators.toArray(new Comparator[comparators.size()])
        );
    }