public static int notNegative(int parameter, String name) {
    if (parameter < 0) {
      throw new IllegalArgumentException(notNegativeMsg(name));
    }
    return parameter;
  }