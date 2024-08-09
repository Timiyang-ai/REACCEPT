public static double notNegativeOrZero(double parameter, double eps, String name) {
    if (CompareUtils.closeEquals(parameter, 0, eps)) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be zero");
    }
    if (parameter < 0) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must be greater than zero");
    }
    return parameter;
  }