public static <E> Comparator<E> chainedComparator(final Comparator<E>... comparators) {
        final ComparatorChain<E> chain = new ComparatorChain<>();
        for (final Comparator<E> comparator : comparators) {
            chain.addComparator(Objects.requireNonNull(comparator, "comparator"));
        }
        return chain;
    }