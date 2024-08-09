public FluentIterable<E> collate(final Iterable<E> other) {
        return of(IterableUtils.collatedIterable(iterable, other, null));
    }