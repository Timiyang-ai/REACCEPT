public static double notNegativeOrZero(double argument, double tolerance, String name) {
    if (DoubleMath.fuzzyEquals(argument, 0, tolerance)) {
      throw new IllegalArgumentException("Argument '" + name + "' must not be zero");
    }
    if (argument < 0) {
      throw new IllegalArgumentException("Argument '" + name + "' must be greater than zero but has value " + argument);
    }
    return argument;
  }