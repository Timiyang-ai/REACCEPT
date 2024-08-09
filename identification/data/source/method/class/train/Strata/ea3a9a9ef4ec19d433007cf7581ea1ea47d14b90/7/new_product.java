public static int notNegativeOrZero(int argument, String name) {
    if (argument <= 0) {
      throw new IllegalArgumentException(notNegativeOrZeroMsg(name, argument));
    }
    return argument;
  }