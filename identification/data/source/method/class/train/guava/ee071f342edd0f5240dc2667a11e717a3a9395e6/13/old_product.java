public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E element) {
    Object[] array = { checkNotNull(element) };
    return new RegularImmutableSortedSet<E>(array, Ordering.natural());
  }