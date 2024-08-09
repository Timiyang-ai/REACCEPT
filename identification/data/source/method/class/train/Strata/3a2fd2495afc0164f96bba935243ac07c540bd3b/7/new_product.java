public static String matches(Pattern pattern, String argument, String name) {
    notNull(pattern, "pattern");
    notNull(argument, name);
    if (!pattern.matcher(argument).matches()) {
      throw new IllegalArgumentException(matchesMsg(pattern, name, argument));
    }
    return argument;
  }