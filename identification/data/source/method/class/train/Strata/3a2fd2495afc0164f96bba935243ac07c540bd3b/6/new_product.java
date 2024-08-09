public static <T> void inOrderOrEqual(Comparable<? super T> obj1, T obj2, String param1, String param2) {
    notNull(obj1, param1);
    notNull(obj2, param2);
    if (obj1.compareTo(obj2) > 0) {
      throw new IllegalArgumentException(
          "Invalid order: Expected '" + param1 + "' <= '" + param2 + "', but found: '" + obj1 + "' > '" + obj2);
    }
  }