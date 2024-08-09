public static <E extends Comparable<? super E>> ImmutableSortedSet<E> copyOf(
      E[] elements) {
    return ofInternal(Ordering.natural(), (Object[]) elements);
  }