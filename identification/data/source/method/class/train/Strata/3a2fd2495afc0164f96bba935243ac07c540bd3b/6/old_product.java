public static <T> void inOrderOrEqual(Comparable<? super T> obj1, T obj2, String param1, String param2) {
    notNull(obj1, param1);
    notNull(obj2, param2);
    if (obj1.compareTo(obj2) > 0) {
      throw new IllegalArgumentException("Input parameter '" + param1 + "' must be before '" + param2 + "'");
    }
  }