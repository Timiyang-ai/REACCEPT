public static <T extends Comparable<? super T>> int nullSafeComparator(final T one, final T two) {
    if (one == null ^ two == null) {
      return (one == null) ? -1 : 1;
    }

    if (one == null && two == null) {
      return 0;
    }

    return one.compareTo(two);
  }