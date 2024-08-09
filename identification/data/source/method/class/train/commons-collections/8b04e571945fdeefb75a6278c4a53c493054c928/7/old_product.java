public FluentIterable<E> zip(final Iterable<E>... others) {
        @SuppressWarnings("unchecked")
        Iterable<E>[] iterables = new Iterable[1 + others.length];
        iterables[0] = iterable;
        System.arraycopy(others, 0, iterables, 1, others.length);
        return of(IterableUtils.zippingIterable(iterables));
    }