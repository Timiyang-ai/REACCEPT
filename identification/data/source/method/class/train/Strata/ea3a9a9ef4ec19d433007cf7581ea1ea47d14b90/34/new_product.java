public static double[] notEmpty(double[] argument, String name) {
    notNull(argument, name);
    if (argument.length == 0) {
      throw new IllegalArgumentException(notEmptyArrayMsg(name));
    }
    return argument;
  }