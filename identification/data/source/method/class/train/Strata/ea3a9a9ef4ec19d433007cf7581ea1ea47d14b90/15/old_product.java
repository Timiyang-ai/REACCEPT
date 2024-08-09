public static double notZero(double parameter, double eps, String name) {
    if (CompareUtils.closeEquals(parameter, 0d, eps)) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be zero");
    }
    return parameter;
  }