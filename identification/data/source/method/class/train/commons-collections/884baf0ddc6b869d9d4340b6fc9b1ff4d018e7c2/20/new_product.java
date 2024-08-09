@SuppressWarnings("unchecked")
    public static <E extends Comparable<? super E>> Comparator<E> chainedComparator(Collection<Comparator<E>> comparators) {
        return chainedComparator(
            (Comparator<E>[]) comparators.toArray(new Comparator[comparators.size()])
        );
    }