public static int[] notEmpty(int[] parameter, String name) {
    notNull(parameter, name);
    if (parameter.length == 0) {
      throw new IllegalArgumentException("Argument array '" + name + "' must not be empty");
    }
    return parameter;
  }