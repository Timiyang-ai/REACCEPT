@EnsuresQualifierIf(result=true, expression="#1", qualifier=Regex.class)
  public static boolean isRegex(final char c) {
    return isRegex(Character.toString(c));
  }