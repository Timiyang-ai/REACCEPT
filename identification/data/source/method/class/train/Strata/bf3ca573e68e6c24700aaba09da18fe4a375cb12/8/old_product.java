public static FxSwap ofForwardPoints(
      CurrencyAmount amountCurrency1,
      Currency currency2,
      double nearFxRate,
      double forwardPoints,
      LocalDate nearDate,
      LocalDate farDate) {

    ArgChecker.isFalse(amountCurrency1.getCurrency().equals(currency2), "Currencies must not be equal");
    ArgChecker.notNegativeOrZero(nearFxRate, "fxRate");
    double farFxRate = nearFxRate + forwardPoints;
    Fx nearLeg = Fx.of(amountCurrency1, currency2, nearFxRate, nearDate);
    Fx farLeg = Fx.of(amountCurrency1.negated(), currency2, farFxRate, farDate);
    return of(nearLeg, farLeg);
  }