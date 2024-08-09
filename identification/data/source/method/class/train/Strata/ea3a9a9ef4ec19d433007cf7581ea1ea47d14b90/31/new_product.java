public static String notBlank(String parameter, String name) {
    notNull(parameter, name);
    if (parameter.trim().isEmpty()) {
      throw new IllegalArgumentException("Argument '" + name + "' must not be empty");
    }
    return parameter;
  }