public FluentIterable<E> filter(final Predicate<E> predicate) {
        return of(IterableUtils.filteredIterable(iterable, predicate));
    }