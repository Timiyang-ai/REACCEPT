public MultiCurrencyAmount plus(CurrencyAmount amountToAdd) {
    ArgChecker.notNull(amountToAdd, "amountToAdd");
    return MultiCurrencyAmount.total(Iterables.concat(amounts, ImmutableList.of(amountToAdd)));
  }