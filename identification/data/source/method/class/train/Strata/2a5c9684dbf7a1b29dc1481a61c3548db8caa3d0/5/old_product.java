public MultiCurrencyAmount plus(MultiCurrencyAmount amountToAdd) {
    ArgChecker.notNull(amountToAdd, "amountToAdd");
    return Stream.concat(amounts.stream(), amountToAdd.stream()).collect(collector());
  }