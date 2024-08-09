public static String notEmpty(String argument, String name) {
    notNull(argument, name);
    if (argument.isEmpty()) {
      throw new IllegalArgumentException(notEmptyMsg(name));
    }
    return argument;
  }