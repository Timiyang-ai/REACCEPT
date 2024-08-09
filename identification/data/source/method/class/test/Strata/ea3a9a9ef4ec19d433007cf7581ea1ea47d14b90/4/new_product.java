public static String notBlank(String argument, String name) {
    notNull(argument, name);
    if (argument.trim().isEmpty()) {
      throw new IllegalArgumentException(notBlankMsg(name));
    }
    return argument;
  }