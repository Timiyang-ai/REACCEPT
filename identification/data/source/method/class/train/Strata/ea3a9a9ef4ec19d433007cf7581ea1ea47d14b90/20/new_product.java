public static String notEmpty(String parameter, String name) {
    notNull(parameter, name);
    if (parameter.isEmpty()) {
      throw new IllegalArgumentException("Argument '" + name + "' must not be empty");
    }
    return parameter;
  }