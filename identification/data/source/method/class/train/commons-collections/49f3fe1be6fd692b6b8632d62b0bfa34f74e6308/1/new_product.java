@SuppressWarnings("unchecked")
    public static <E> E max(E o1, E o2, Comparator<E> comparator) {
        if (comparator == null) {
            comparator = NATURAL_COMPARATOR;
        }
        int c = comparator.compare(o1, o2);
        return (c > 0) ? o1 : o2;
    }