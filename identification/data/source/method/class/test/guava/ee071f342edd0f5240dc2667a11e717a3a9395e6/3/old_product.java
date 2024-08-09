public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E... elements) {
    return ofInternal(Ordering.natural(), elements);
  }