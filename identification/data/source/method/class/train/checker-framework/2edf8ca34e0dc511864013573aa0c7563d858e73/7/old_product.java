@SuppressWarnings("regex")    // RegexUtil
  /*@checkers.quals.Pure*/
  public static boolean isRegex(char c) {
    return isRegex(Character.toString(c));
  }