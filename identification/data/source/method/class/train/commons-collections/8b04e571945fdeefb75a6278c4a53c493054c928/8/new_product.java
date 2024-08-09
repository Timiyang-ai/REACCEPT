public FluentIterable<E> collate(final Iterable<? extends E> other) {
        return of(IterableUtils.collatedIterable(null, iterable, other));
    }