public static double notNegativeOrZero(double argument, String name) {
    if (argument <= 0) {
      throw new IllegalArgumentException(notNegativeOrZeroMsg(name, argument));
    }
    return argument;
  }