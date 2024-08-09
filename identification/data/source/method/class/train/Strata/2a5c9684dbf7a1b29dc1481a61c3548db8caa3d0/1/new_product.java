public static MultiCurrencyAmount total(Iterable<CurrencyAmount> amounts) {
    ArgChecker.notNull(amounts, "amounts");
    return Guavate.stream(amounts).collect(toMultiCurrencyAmount());
  }