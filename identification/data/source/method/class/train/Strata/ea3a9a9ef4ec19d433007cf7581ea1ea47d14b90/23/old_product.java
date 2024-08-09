public static String notBlank(String parameter, String name) {
    notNull(parameter, name);
    if (parameter.trim().length() == 0) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be empty");
    }
    return parameter;
  }