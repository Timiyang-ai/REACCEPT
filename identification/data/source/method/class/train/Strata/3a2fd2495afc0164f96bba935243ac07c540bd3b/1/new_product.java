public static String matches(Pattern pattern, String parameter, String name) {
    notNull(pattern, "pattern");
    notNull(parameter, name);
    if (pattern.matcher(parameter).matches() == false) {
      throw new IllegalArgumentException(matchesMsg(pattern, name));
    }
    return parameter;
  }