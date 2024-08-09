public static String notBlank(String parameter, String name) {
    notNull(parameter, name);
    parameter = CharMatcher.WHITESPACE.trimFrom(parameter);
    if (parameter.length() == 0) {
      throw new IllegalArgumentException("Input parameter '" + name + "' must not be empty");
    }
    return parameter;
  }