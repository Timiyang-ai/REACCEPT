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
    double farFxRate = nearFxRate + forwardPoints;
    FxSingle nearLeg = FxSingle.of(amountCurrency1, FxRate.of(currency1, currency2, nearFxRate), nearDate);
    FxSingle farLeg = FxSingle.of(amountCurrency1.negated(), FxRate.of(currency1, currency2, farFxRate), farDate);
    return of(nearLeg, farLeg);
  }