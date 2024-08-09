public static FxSwap ofForwardPoints(
      CurrencyAmount amount,
      FxRate nearRate,
      double decimalForwardPoints,
      LocalDate nearDate,
      LocalDate farDate) {

    Currency currency1 = amount.getCurrency();
    ArgChecker.isTrue(
        nearRate.getPair().contains(currency1),
        Messages.format("Amount and FX rate have no currency in common: {} and {}", amount, nearRate));
    FxRate farRate = FxRate.of(nearRate.getPair(), nearRate.fxRate(nearRate.getPair()) + decimalForwardPoints);
    FxSingle nearLeg = FxSingle.of(amount, nearRate, nearDate);
    FxSingle farLeg = FxSingle.of(amount.negated(), farRate, farDate);
    return of(nearLeg, farLeg);
  }