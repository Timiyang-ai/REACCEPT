public static <E extends Comparable<?>> Builder<E> reverseOrder() {
    return new Builder<E>(Collections.reverseOrder());
  }