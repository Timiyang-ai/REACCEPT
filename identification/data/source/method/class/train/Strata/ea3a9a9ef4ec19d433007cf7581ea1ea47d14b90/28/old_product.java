public static double[] notEmpty(double[] parameter, String name) {
    notNull(parameter, name);
    if (parameter.length == 0) {
      throw new IllegalArgumentException("Input parameter array '" + name + "' must not be empty");
    }
    return parameter;
  }