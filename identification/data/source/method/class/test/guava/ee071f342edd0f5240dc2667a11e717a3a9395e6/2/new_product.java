@SuppressWarnings("unchecked")
  public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E e1, E e2, E e3, E e4, E e5) {
    return construct(Ordering.natural(), 5, e1, e2, e3, e4, e5);
  }