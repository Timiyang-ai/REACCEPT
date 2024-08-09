@Pure
  @EnsuresAnnotationIf(result=true, expression="#1", annotation=Regex.class)
  public static boolean isRegex(String s) {
    return isRegex(s, 0);
  }