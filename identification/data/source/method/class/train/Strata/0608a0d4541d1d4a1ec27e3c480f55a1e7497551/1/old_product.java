@FromString
  public static CurrencyPair parse(String pairStr) {
    ArgChecker.notNull(pairStr, "pairStr");
    Matcher matcher = REGEX_FORMAT.matcher(pairStr.toUpperCase(Locale.ENGLISH));
    if (matcher.matches() == false) {
      throw new IllegalArgumentException("Invalid currency pair: " + pairStr);
    }
    Currency base = Currency.parse(matcher.group(1));
    Currency counter = Currency.parse(matcher.group(2));
    return new CurrencyPair(base, counter);
  }