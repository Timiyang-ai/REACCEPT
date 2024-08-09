public static FxRate parse(String rateStr) {
    ArgChecker.notNull(rateStr, "rateStr");
    Matcher matcher = REGEX_FORMAT.matcher(rateStr.toUpperCase(Locale.ENGLISH));
    if (matcher.matches() == false) {
      throw new IllegalArgumentException("Invalid rate: " + rateStr);
    }
    try {
      Currency base = Currency.parse(matcher.group(1));
      Currency counter = Currency.parse(matcher.group(2));
      double rate = Double.parseDouble(matcher.group(3));
      return new FxRate(CurrencyPair.of(base, counter), rate);
    } catch (RuntimeException ex) {
      throw new IllegalArgumentException("Unable to parse rate: " + rateStr, ex);
    }
  }