public static void isFalse(boolean validIfFalse, String message, Object... arg) {
    // return void, not the parameter, as no need to check a boolean method parameter
    if (validIfFalse) {
      throw new IllegalArgumentException(formatMessage(message, arg));
    }
  }