public static double notZero(double parameter, double tolerance, String name) {
    if (DoubleMath.fuzzyEquals(parameter, 0d, tolerance)) {
      throw new IllegalArgumentException("Argument '" + name + "' must not be zero");
    }
    return parameter;
  }