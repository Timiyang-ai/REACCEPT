public static double notNegative(double parameter, String name) {
    if (parameter < 0) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be negative");
    }
    return parameter;
  }