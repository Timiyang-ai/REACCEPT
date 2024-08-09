@FromString
  public static Tenor parse(String toParse) {
    return new Tenor(Period.parse(ArgChecker.notNull(toParse, "toParse")));
  }