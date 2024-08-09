public static Comparator nullHighComparator(Comparator comparator) {
        if (comparator == null) {
            comparator = NATURAL;
        }
        return new NullComparator(comparator, true);
    }