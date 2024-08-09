public CurrencyAmount minus(CurrencyAmount amountToSubtract) {
    ArgChecker.notNull(amountToSubtract, "amountToAdd");
    ArgChecker.isTrue(amountToSubtract.getCurrency().equals(currency), "Unable to subtract amounts in different currencies");
    return minus(amountToSubtract.getAmount());
  }