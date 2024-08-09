public static int notNegative(int argument, String name) {
    if (argument < 0) {
      throw new IllegalArgumentException(notNegativeMsg(name));
    }
    return argument;
  }