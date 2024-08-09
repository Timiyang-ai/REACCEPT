public static FxSwap ofForwardPoints(
      CurrencyAmount amount,
      FxRate nearRate,
      double decimalForwardPoints,
      LocalDate nearDate,
      LocalDate farDate,
      BusinessDayAdjustment paymentDateAdjustment) {

    FxRate farRate = FxRate.of(nearRate.getPair(), nearRate.fxRate(nearRate.getPair()) + decimalForwardPoints);
    return of(amount, nearRate, nearDate, farRate, farDate, paymentDateAdjustment);
  }