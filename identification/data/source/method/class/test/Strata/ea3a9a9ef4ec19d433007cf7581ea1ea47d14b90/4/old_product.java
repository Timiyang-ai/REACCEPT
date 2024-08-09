public static String notBlank(String parameter, String name) {
    notNull(parameter, name);
    if (parameter.trim().isEmpty()) {
      throw new IllegalArgumentException(notBlankMsg(name));
    }
    return parameter;
  }