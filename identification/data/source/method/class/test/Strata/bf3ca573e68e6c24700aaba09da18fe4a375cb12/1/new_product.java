public static FxSwap ofForwardPoints(
      CurrencyAmount amount,
      FxRate nearRate,
      double forwardPoints,
      LocalDate nearDate,
      LocalDate farDate) {

    Currency currency1 = amount.getCurrency();
    ArgChecker.isTrue(
        nearRate.getPair().contains(currency1),
        Messages.format("Amount and FX rate have a currency in common: {} and {}", amount, nearDate));
    FxRate farRate = nearRate.mapRate(rate -> rate + forwardPoints);
    FxSingle nearLeg = FxSingle.of(amount, nearRate, nearDate);
    FxSingle farLeg = FxSingle.of(amount.negated(), farRate, farDate);
    return of(nearLeg, farLeg);
  }