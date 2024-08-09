public static Object range(Object to) {
    checkRangeTypes(to, "to");
    if (to instanceof Integer) {
      return new IntRange((Integer) to);
    }
    if (to instanceof Long) {
      return new LongRange((Long) to);
    }
    if (to instanceof BigInteger) {
      return new BigIntegerRange((BigInteger) to);
    }
    return new CharRange((Character) to);
  }