public FluentIterable<E> filter(final Predicate<? super E> predicate) {
        return of(IterableUtils.filteredIterable(iterable, predicate));
    }