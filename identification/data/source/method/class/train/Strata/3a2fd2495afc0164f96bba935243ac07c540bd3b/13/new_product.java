public static double notNegative(double parameter, String name) {
    if (parameter < 0) {
      throw new IllegalArgumentException("Argument '" + name + "' must not be negative");
    }
    return parameter;
  }