public FluentIterable<E> limit(final long maxSize) {
        return of(IterableUtils.boundedIterable(iterable, maxSize));
    }