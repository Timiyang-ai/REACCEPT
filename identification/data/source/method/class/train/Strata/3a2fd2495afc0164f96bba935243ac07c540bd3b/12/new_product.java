public static double notNegative(double argument, String name) {
    if (argument < 0) {
      throw new IllegalArgumentException(notNegativeMsg(name));
    }
    return argument;
  }