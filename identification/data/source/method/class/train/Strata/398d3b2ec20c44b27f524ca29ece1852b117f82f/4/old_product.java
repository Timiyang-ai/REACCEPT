@FromString
  public static Tenor parse(String toParse) {
    ArgChecker.notNull(toParse, "toParse");
    String prefixed = toParse.startsWith("P") ? toParse : "P" + toParse;
    return new Tenor(Period.parse(prefixed));
  }