public static String matches(Pattern pattern, String argument, String name) {
    notNull(pattern, "pattern");
    notNull(argument, name);
    if (pattern.matcher(argument).matches() == false) {
      throw new IllegalArgumentException(matchesMsg(pattern, name));
    }
    return argument;
  }