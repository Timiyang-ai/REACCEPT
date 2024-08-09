@FromString
  public static CurrencyAmount parse(String amountStr) {
    // this method has had some performance optimizations applied
    if (amountStr == null || amountStr.length() <= 4 || amountStr.charAt(3) != ' ') {
      throw new IllegalArgumentException("Unable to parse amount, invalid format: " + amountStr);
    }

    String currencyCode = amountStr.substring(0, 3);
    String doubleString = amountStr.substring(4);
    if (doubleString.indexOf(' ') != -1) {
      throw new IllegalArgumentException("Unable to parse amount, invalid format: " + amountStr);
    }

    try {
      Currency cur = Currency.parse(currencyCode);
      double amount = Double.parseDouble(doubleString);
      return new CurrencyAmount(cur, amount);
    } catch (RuntimeException ex) {
      throw new IllegalArgumentException("Unable to parse amount: " + amountStr, ex);
    }
  }