@EnsuresQualifierIf(result=true, expression="#1",
          qualifier=org.checkerframework.checker.experimental.regex_qual_poly.qual.Regex.class)
  public static boolean isRegex(String s) {
    return org.checkerframework.checker.regex.RegexUtil.isRegex(s, 0);
  }