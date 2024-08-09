public MultiCurrencyAmount plus(MultiCurrencyAmount amountToAdd) {
    ArgChecker.notNull(amountToAdd, "amountToAdd");
    return MultiCurrencyAmount.total(Iterables.concat(amounts, amountToAdd.amounts));
  }