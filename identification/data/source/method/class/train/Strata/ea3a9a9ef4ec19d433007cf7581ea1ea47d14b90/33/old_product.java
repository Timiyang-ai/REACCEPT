public static double notNegativeOrZero(double parameter, double tolerance, String name) {
    if (DoubleMath.fuzzyEquals(parameter, 0, tolerance)) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be zero");
    }
    if (parameter < 0) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must be greater than zero");
    }
    return parameter;
  }