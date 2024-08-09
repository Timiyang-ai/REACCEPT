public static SecurityPriceInfo ofCurrencyMinorUnit(Currency currency) {
    int digits = currency.getMinorUnitDigits();
    double unitAmount = 1;
    for (int i = 0; i < digits; i++) {
      unitAmount = unitAmount / 10;
    }
    return new SecurityPriceInfo(unitAmount, CurrencyAmount.of(currency, unitAmount), 1);
  }