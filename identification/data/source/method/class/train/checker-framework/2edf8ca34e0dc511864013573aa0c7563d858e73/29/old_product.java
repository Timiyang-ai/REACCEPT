@SideEffectFree
  public static /*@Regex*/ String asRegex(String s, int groups) {
    try {
      Pattern p = Pattern.compile(s);
      int actualGroups = getGroupCount(p);
      if (actualGroups < groups) {
        throw new Error(regexErrorMessage(s, groups, actualGroups));
      }
      return s;
    } catch (PatternSyntaxException e) {
      throw new Error(e);
    }
  }