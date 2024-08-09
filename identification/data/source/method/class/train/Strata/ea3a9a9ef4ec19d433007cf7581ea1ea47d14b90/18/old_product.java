public static int notNegativeOrZero(int parameter, String name) {
    if (parameter <= 0) {
      throw new IllegalArgumentException(notNegativeOrZeroMsg(name));
    }
    return parameter;
  }