@SuppressWarnings("unchecked")
    public static <E extends Comparable<? super E>> Comparator<E> chainedComparator(Comparator<E> comparator1, Comparator<E> comparator2) {
        return chainedComparator(new Comparator[] {comparator1, comparator2});
    }