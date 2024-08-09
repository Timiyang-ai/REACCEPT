public MultiCurrencyAmount plus(CurrencyAmount amountToAdd) {
    ArgChecker.notNull(amountToAdd, "amountToAdd");
    return Stream.concat(amounts.stream(), Stream.of(amountToAdd)).collect(collector());
  }