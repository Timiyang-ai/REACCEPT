public static Currency parseCurrency(String str) {
    try {
      return Currency.of(str.toUpperCase(Locale.ENGLISH));
    } catch (RuntimeException ex) {
      throw new IllegalArgumentException(
          "Unknown Currency, must be 3 letter ISO-4217 format but was '" + str + "'");
    }
  }