public FluentIterable<E> zip(final Iterable<? extends E> other) {
        return of(IterableUtils.zippingIterable(iterable, other));
    }