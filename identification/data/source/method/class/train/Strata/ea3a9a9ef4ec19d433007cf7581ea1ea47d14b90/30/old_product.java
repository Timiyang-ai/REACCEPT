public static String notEmpty(String parameter, String name) {
    notNull(parameter, name);
    if (parameter.length() == 0) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be empty");
    }
    return parameter;
  }