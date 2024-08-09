public static <E> Iterable<E> beforeEach(final E what, Iterable<E> sequence) {
    Preconditions.checkNotNull(what);
    return Iterables.concat(
        Iterables.transform(sequence, element -> ImmutableList.of(what, element)));
  }