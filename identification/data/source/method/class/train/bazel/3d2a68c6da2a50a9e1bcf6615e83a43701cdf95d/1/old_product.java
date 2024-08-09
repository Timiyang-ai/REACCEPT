public static <E> Iterable<E> beforeEach(final E what, Iterable<E> sequence) {
    Preconditions.checkNotNull(what);
    return Iterables.concat(
        Iterables.transform(
            sequence,
            new Function<E, Iterable<E>>() {
              @Override
              public Iterable<E> apply(E element) {
                return ImmutableList.of(what, element);
              }
            }
        ));
  }