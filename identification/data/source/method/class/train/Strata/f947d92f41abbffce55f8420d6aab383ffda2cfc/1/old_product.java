@FromString
  public static CurrencyAmount parse(String amountStr) {
    ArgChecker.notNull(amountStr, "amountStr");
    List<String> split = Splitter.on(' ').splitToList(amountStr);
    if (split.size() != 2) {
      throw new IllegalArgumentException("Unable to parse amount, invalid format: " + amountStr);
    }
    try {
      Currency cur = Currency.parse(split.get(0));
      double amount = Double.parseDouble(split.get(1));
      return new CurrencyAmount(cur, amount);
    } catch (RuntimeException ex) {
      throw new IllegalArgumentException("Unable to parse amount: " + amountStr, ex);
    }
  }