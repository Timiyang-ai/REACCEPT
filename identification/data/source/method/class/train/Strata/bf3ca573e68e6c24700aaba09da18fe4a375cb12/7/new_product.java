public static FxSwap ofForwardPoints(
      CurrencyAmount amountCurrency1,
      FxRate nearRate,
      double forwardPoints,
      LocalDate nearDate,
      LocalDate farDate,
      BusinessDayAdjustment paymentDateAdjustment) {

    ArgChecker.notNull(paymentDateAdjustment, "paymentDateAdjustment");
    Currency currency1 = amountCurrency1.getCurrency();
    ArgChecker.isTrue(
        nearRate.getPair().contains(currency1),
        Messages.format("Amount and FX rate have a currency in common: {} and {}", amountCurrency1, nearDate));
    FxRate farRate = nearRate.mapRate(rate -> rate + forwardPoints);
    FxSingle nearLeg = FxSingle.of(amountCurrency1, nearRate, nearDate, paymentDateAdjustment);
    FxSingle farLeg = FxSingle.of(amountCurrency1.negated(), farRate, farDate, paymentDateAdjustment);
    return of(nearLeg, farLeg);
  }