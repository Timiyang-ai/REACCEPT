public static double notZero(double parameter, double tolerance, String name) {
    if (CompareUtils.closeEquals(parameter, 0d, tolerance)) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be zero");
    }
    return parameter;
  }