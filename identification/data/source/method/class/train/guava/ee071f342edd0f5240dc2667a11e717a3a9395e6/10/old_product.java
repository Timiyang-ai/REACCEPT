public static <E extends Comparable<? super E>> ImmutableSortedSet<E> copyOf(
      E[] elements) {
    return copyOf(Ordering.natural(), Arrays.asList(elements));
  }