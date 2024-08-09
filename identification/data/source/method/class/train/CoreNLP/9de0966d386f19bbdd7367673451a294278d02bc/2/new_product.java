public static <T extends Comparable<? super T>> int nullSafeCompare(final T one, final T two) {
    if (one == null) {
      if (two == null) {
        return 0;
      }
      return -1;
    } else {
      if (two == null) {
        return 1;
      }
      return one.compareTo(two);
    }
  }