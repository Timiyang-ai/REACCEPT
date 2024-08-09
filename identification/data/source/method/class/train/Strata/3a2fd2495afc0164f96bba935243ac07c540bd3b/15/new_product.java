public static <T> void inOrderOrEqual(Comparable<? super T> obj1, T obj2, String name1, String name2) {
    notNull(obj1, name1);
    notNull(obj2, name2);
    if (obj1.compareTo(obj2) > 0) {
      throw new IllegalArgumentException(
          Messages.format("Invalid order: Expected '{}' <= '{}', but found: '{}' > '{}", name1, name2, obj1, obj2));
    }
  }