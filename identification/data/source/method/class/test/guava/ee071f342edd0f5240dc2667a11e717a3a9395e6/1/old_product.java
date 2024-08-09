@SuppressWarnings("unchecked")
  public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E e1, E e2, E e3) {
    return ofInternal(Ordering.natural(), e1, e2, e3);
  }