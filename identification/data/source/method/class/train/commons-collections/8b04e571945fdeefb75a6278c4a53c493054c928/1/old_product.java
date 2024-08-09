public FluentIterable<E> collate(final Iterable<E> other, Comparator<? super E> comparator) {
        return of(IterableUtils.collatedIterable(iterable, other, comparator));
    }