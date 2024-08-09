public static String notEmpty(String parameter, String name) {
    notNull(parameter, name);
    if (parameter.isEmpty()) {
      throw new IllegalArgumentException(notEmptyMsg(name));
    }
    return parameter;
  }