public static double notNegativeOrZero(double parameter, String name) {
    if (parameter <= 0) {
      throw new IllegalArgumentException(notNegativeOrZeroMsg(name));
    }
    return parameter;
  }