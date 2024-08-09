@SuppressWarnings({"regex","deterministic"})    // RegexUtil; for purity, catches an exception
  @Pure
  // No @EnsuresQualifierIf annotation because this method is special-cased
  // in RegexTransfer.
  // @EnsuresQualifierIf(result=true, expression="#1", qualifier=Regex.class)
  public static boolean isRegex(String s, int groups) {
    Pattern p;
    try {
      p = Pattern.compile(s);
    } catch (PatternSyntaxException e) {
      return false;
    }
    return getGroupCount(p) >= groups;
  }