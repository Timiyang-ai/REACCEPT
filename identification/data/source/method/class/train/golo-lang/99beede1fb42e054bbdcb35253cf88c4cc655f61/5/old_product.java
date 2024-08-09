public static Object range(Object to) {
    require((to instanceof Integer) || (to instanceof Long) || (to instanceof Character),
        "to must either be an Integer, Long or Character");
    if (to instanceof Integer) {
      return new IntRange((Integer) to);
    }
    if (to instanceof Long) {
      return new LongRange((Long) to);
    } else {
      return new CharRange((Character) to);
    }
  }