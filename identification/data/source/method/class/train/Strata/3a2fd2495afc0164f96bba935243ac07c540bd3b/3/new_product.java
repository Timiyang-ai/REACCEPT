public static long notNegative(long argument, String name) {
    if (argument < 0) {
      throw new IllegalArgumentException(notNegativeMsg(name));
    }
    return argument;
  }