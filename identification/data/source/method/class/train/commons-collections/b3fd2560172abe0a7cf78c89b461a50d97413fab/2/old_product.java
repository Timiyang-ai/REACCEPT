public FluentIterable<E> skip(int elementsToSkip) {
        return of(IterableUtils.skippingIterable(iterable, elementsToSkip));
    }