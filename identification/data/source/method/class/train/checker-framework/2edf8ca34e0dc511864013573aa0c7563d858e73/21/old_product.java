@EnsuresQualifierIf(result=true, expression="#1", qualifier=org.checkerframework.checker.experimental.regex_qual.qual.Regex.class)
  public static boolean isRegex(String s) {
    return isRegex(s, 0);
  }