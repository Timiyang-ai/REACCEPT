@Pure
  public static boolean isRegex(String s, int groups) {
    Pattern p;
    try {
      p = Pattern.compile(s);
    } catch (PatternSyntaxException e) {
      return false;
    }
    return getGroupCount(p) >= groups;
  }