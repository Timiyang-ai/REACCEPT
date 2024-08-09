@SuppressWarnings("regex")    // suppresses warnings
  /*@Pure*/
  public static /*@Regex*/ String asRegex(String s) {
    try {
      Pattern.compile(s);
      return s;
    } catch (PatternSyntaxException e) {
      throw new Error(e);
    }
  }