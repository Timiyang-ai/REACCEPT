@FromString
  public static Tenor parse(String toParse) {
    ArgChecker.notNull(toParse, "toParse");
    String prefixed = toParse.startsWith("P") ? toParse : "P" + toParse;
    try {
      return Tenor.of(Period.parse(prefixed));
    } catch (DateTimeParseException ex) {
      throw new IllegalArgumentException(ex);
    }
  }