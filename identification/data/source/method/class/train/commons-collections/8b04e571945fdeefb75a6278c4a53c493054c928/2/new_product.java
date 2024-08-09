public FluentIterable<E> zip(final Iterable<? extends E>... others) {
        return of(IterableUtils.zippingIterable(iterable, others));
    }