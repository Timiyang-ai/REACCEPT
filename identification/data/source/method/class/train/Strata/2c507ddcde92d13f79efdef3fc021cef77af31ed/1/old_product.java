@FromString
  public static Frequency parse(String toParse) {
    ArgChecker.notNull(toParse, "toParse");
    if (toParse.equalsIgnoreCase("Term")) {
      return TERM;
    }
    String prefixed = toParse.startsWith("P") ? toParse : "P" + toParse;
    try {
      return Frequency.of(Period.parse(prefixed));
    } catch (DateTimeParseException ex) {
      throw new IllegalArgumentException(ex);
    }
  }