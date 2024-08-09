@SuppressWarnings("unchecked")
  public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E e1, E e2, E e3, E e4, E e5, E e6, E... remaining) {
    int size = remaining.length + 6;
    List<E> all = new ArrayList<E>(size);
    Collections.addAll(all, e1, e2, e3, e4, e5, e6);
    Collections.addAll(all, remaining);
    // This is a mess (see TODO at top of file)
    return ofInternal(Ordering.natural(),
        (Object[]) all.toArray(new Comparable[0]));
  }