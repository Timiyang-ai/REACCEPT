public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E element) {
    return new RegularImmutableSortedSet<E>(
        ImmutableList.of(element), Ordering.natural());
  }