public FluentIterable<E> limit(final int maxSize) {
        return of(IterableUtils.boundedIterable(iterable, maxSize));
    }