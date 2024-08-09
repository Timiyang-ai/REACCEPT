public static <E, T extends E> int cardinality(final Iterable<E> iterable, final T obj) {
        if (iterable instanceof Set<?>) {
            return ((Set<E>) iterable).contains(obj) ? 1 : 0;
        }
        if (iterable instanceof Bag<?>) {
            return ((Bag<E>) iterable).getCount(obj);
        }
        return size(filteredIterable(iterable, EqualPredicate.<E>equalPredicate(obj)));
    }