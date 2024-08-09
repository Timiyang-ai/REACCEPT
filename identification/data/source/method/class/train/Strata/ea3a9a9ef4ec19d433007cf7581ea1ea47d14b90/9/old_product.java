public static long notNegativeOrZero(long parameter, String name) {
    if (parameter <= 0) {
      throw new IllegalArgumentException("Argument '" + name + "' must not be negative or zero");
    }
    return parameter;
  }