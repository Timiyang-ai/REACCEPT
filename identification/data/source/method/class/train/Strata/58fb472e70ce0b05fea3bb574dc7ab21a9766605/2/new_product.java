public static void isTrue(boolean validIfTrue, String message, Object... arg) {
    // return void, not the parameter, as no need to check a boolean method parameter
    if (!validIfTrue) {
      throw new IllegalArgumentException(Messages.format(message, arg));
    }
  }