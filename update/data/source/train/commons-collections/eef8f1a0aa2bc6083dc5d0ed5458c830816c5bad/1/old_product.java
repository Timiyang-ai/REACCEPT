public static <E> Iterator<E> collatedIterator(final Comparator<? super E> comparator,
                                                   final Iterator<? extends E>... iterators) {
        @SuppressWarnings("unchecked")
        final Comparator<E> comp =
            comparator == null ? ComparatorUtils.NATURAL_COMPARATOR : (Comparator<E>) comparator;
        return new CollatingIterator<E>(comp, iterators);
    }