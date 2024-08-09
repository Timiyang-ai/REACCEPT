public static FxSwap ofForwardPoints(
      CurrencyAmount amount,
      FxRate nearRate,
      double decimalForwardPoints,
      LocalDate nearDate,
      LocalDate farDate,
      BusinessDayAdjustment paymentDateAdjustment) {

    ArgChecker.notNull(paymentDateAdjustment, "paymentDateAdjustment");
    Currency currency1 = amount.getCurrency();
    ArgChecker.isTrue(
        nearRate.getPair().contains(currency1),
        Messages.format("Amount and FX rate have a currency in common: {} and {}", amount, nearDate));
    FxRate farRate = FxRate.of(nearRate.getPair(), nearRate.fxRate(nearRate.getPair()) + decimalForwardPoints);
    FxSingle nearLeg = FxSingle.of(amount, nearRate, nearDate, paymentDateAdjustment);
    FxSingle farLeg = FxSingle.of(amount.negated(), farRate, farDate, paymentDateAdjustment);
    return of(nearLeg, farLeg);
  }