public FluentIterable<E> collate(final Iterable<? extends E> other,
                                     final Comparator<? super E> comparator) {
        return of(IterableUtils.collatedIterable(comparator, iterable, other));
    }