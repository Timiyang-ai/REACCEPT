public static Object min(Object o1, Object o2, Comparator comparator) {
        if (comparator == null) {
            comparator = NATURAL_COMPARATOR;
        }
        int c = comparator.compare(o1, o2);
        return (c < 0) ? o1 : o2;
    }