public FluentIterable<E> skip(final long elementsToSkip) {
        return of(IterableUtils.skippingIterable(iterable, elementsToSkip));
    }