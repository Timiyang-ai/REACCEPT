public static <E extends Comparable<E>> Builder<E> reverseOrder() {
    return new Builder<E>(Ordering.natural().reverse());
  }