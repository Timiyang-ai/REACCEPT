@EnsuresQualifiersIf({
          @EnsuresQualifierIf(result=true, expression="#1", qualifier=Regex.class),
          @EnsuresQualifierIf(result=true, expression="#1", qualifier=org.checkerframework.checker.experimental.regex_qual.qual.Regex.class),
          @EnsuresQualifierIf(result=true, expression="#1", qualifier=org.checkerframework.checker.experimental.regex_qual_poly.qual.Regex.class)})
  public static boolean isRegex(final char c) {
    return isRegex(Character.toString(c));
  }