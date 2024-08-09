public static Comparator nullLowComparator(Comparator comparator) {
        if (comparator == null) {
            comparator = NATURAL;
        }
        return new NullComparator(comparator, false);
    }