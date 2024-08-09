@SuppressWarnings("unchecked")
  public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(
      E e1, E e2, E e3, E e4, E e5, E e6, E... remaining) {
    Comparable[] contents = new Comparable[6 + remaining.length];
    contents[0] = e1;
    contents[1] = e2;
    contents[2] = e3;
    contents[3] = e4;
    contents[4] = e5;
    contents[5] = e6;
    System.arraycopy(remaining, 0, contents, 6, remaining.length);
    return construct(Ordering.natural(), contents.length, (E[]) contents);
  }