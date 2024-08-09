public static long[] notEmpty(long[] parameter, String name) {
    notNull(parameter, name);
    if (parameter.length == 0) {
      throw new IllegalArgumentException(notEmptyArrayMsg(name));
    }
    return parameter;
  }