public static SecurityPriceInfo ofCurrencyMinorUnit(Currency currency) {
    int digits = currency.getMinorUnitDigits();
    double unitAmount = Math.pow(10, -digits);
    return new SecurityPriceInfo(unitAmount, CurrencyAmount.of(currency, unitAmount), 1);
  }