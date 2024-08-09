public static FxSwap ofForwardPoints(
      CurrencyAmount amountCurrency1,
      Currency currency2,
      double nearFxRate,
      double forwardPoints,
      LocalDate nearDate,
      LocalDate farDate) {

    Currency currency1 = amountCurrency1.getCurrency();
    ArgChecker.isFalse(currency1.equals(currency2), "Currencies must not be equal");
    ArgChecker.notNegativeOrZero(nearFxRate, "fxRate");
    FxRate nearRate = FxRate.of(currency1, currency2, nearFxRate);
    FxRate farRate = FxRate.of(currency1, currency2, nearFxRate + forwardPoints);
    FxSingle nearLeg = FxSingle.of(amountCurrency1, nearRate, nearDate);
    FxSingle farLeg = FxSingle.of(amountCurrency1.negated(), farRate, farDate);
    return of(nearLeg, farLeg);
  }