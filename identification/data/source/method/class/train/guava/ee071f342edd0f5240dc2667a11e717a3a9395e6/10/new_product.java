public static <E extends Comparable<? super E>> ImmutableSortedSet<E> copyOf(
      E[] elements) {
    return construct(Ordering.natural(), elements.length, elements.clone());
  }