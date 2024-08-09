public static double notZero(double argument, double tolerance, String name) {
    if (DoubleMath.fuzzyEquals(argument, 0d, tolerance)) {
      throw new IllegalArgumentException("Argument '" + name + "' must not be zero");
    }
    return argument;
  }