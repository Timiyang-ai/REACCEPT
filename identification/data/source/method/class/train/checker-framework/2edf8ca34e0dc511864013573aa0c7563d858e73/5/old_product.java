@SuppressWarnings("regex")    // tests whether s is a regex
  /*@Pure*/
  public static boolean isRegex(String s) {
    try {
      Pattern.compile(s);
    } catch (PatternSyntaxException e) {
      return false;
    }
    return true;
  }