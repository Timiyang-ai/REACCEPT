@Deprecated
  public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E[] elements) {
    return copyOf(elements);
  }