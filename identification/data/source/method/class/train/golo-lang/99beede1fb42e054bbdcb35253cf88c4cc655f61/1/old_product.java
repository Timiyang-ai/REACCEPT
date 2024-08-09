public static Object range(Object from, Object to) {
    require((from instanceof Integer) || (from instanceof Long) || (from instanceof Character),
        "from must either be an Integer, Long or Character");
    require((to instanceof Integer) || (to instanceof Long) || (to instanceof Character),
        "to must either be an Integer, Long or Character");
    if (((to instanceof Character) || (from instanceof Character))) {
      if (((to instanceof Character) && (from instanceof Character))) {
        return new CharRange((Character) from, (Character) to);
      } else {
        throw new IllegalArgumentException("both bounds must be char for a char range");
      }
    }
    if ((to instanceof Long) && (from instanceof Long)) {
      return new LongRange((Long) from, (Long) to);
    } else if ((to instanceof Integer) && (from instanceof Integer)) {
      return new IntRange((Integer) from, (Integer) to);
    } else if (from instanceof Long) {
      return new LongRange((Long) from, (Integer) to);
    } else {
      return new LongRange((Integer) from, (Long) to);
    }
  }