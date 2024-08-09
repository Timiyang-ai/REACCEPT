public static long notNegativeOrZero(long argument, String name) {
    if (argument <= 0) {
      throw new IllegalArgumentException(notNegativeOrZeroMsg(name));
    }
    return argument;
  }