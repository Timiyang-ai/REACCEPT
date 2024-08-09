public static Object range(Object from, Object to) {
    require((from instanceof Integer) || (from instanceof Long), "from must either be an Integer or a Long");
    require((to instanceof Integer) || (to instanceof Long), "to must either be an Integer or a Long");
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