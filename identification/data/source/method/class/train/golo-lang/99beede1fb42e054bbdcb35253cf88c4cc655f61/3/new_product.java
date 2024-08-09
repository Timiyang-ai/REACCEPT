public static Object range(Object from, Object to) {
    checkRangeTypes(from, "from");
    checkRangeTypes(to, "to");
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
    if (from instanceof BigInteger || to instanceof BigInteger) {
      return new BigIntegerRange(bigIntegerValue(from), bigIntegerValue(to));
    }
    if (from instanceof Long) {
      return new LongRange((Long) from, (Integer) to);
    }
    return new LongRange((Integer) from, (Long) to);
  }