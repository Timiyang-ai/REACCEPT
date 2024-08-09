public static <E> Comparator<E> chainedComparator(final Comparator<E>... comparators) {
        final ComparatorChain<E> chain = new ComparatorChain<E>();
        for (final Comparator<E> comparator : comparators) {
            if (comparator == null) {
                throw new NullPointerException("Comparator cannot be null");
            }
            chain.addComparator(comparator);
        }
        return chain;
    }