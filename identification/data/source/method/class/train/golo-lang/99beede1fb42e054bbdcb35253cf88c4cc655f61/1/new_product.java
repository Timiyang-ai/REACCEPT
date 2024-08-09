public static Object range(Object from, Object to) {
    require((from instanceof Integer) || (from instanceof Long) || (from instanceof Character),
        "from must either be an Integer, Long or Character");
    require((to instanceof Integer) || (to instanceof Long) || (to instanceof Character),
        "to must either be an Integer, Long or Character");

    if ((from instanceof Character && !(to instanceof Character))
        || (to instanceof Character && !(from instanceof Character))) {
      throw new IllegalArgumentException("both bounds must be char for a char range");
    }
    if (to instanceof Character && from instanceof Character) {
      return new CharRange((Character) from, (Character) to);
    }
    if (to instanceof Integer && from instanceof Integer) {
      return new IntRange((Integer) from, (Integer) to);
    }
    if (to instanceof Long && from instanceof Long) {
      return new LongRange((Long) from, (Long) to);
    }
    if (from instanceof Long) {
      return new LongRange((Long) from, (Integer) to);
    }
    return new LongRange((Integer) from, (Long) to);
  }