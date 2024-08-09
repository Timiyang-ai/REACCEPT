public static <E extends Comparable<? super E>> Comparator<E> chainedComparator(Comparator<E>[] comparators) {
        ComparatorChain<E> chain = new ComparatorChain<E>();
        for (int i = 0; i < comparators.length; i++) {
            if (comparators[i] == null) {
                throw new NullPointerException("Comparator cannot be null");
            }
            chain.addComparator(comparators[i]);
        }
        return chain;
    }