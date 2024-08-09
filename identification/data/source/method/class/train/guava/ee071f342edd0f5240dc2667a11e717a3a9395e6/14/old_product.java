public static <E extends Comparable<E>> Builder<E> naturalOrder() {
    return new Builder<E>(Ordering.natural());
  }