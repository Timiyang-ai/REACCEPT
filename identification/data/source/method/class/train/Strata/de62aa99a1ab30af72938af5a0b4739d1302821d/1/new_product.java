@Override
  public ZeroRateSensitivity zeroRatePointSensitivity(double yearFraction, Currency sensitivityCurrency) {
    double discountFactor = discountFactor(yearFraction);
    return ZeroRateSensitivity.of(currency, yearFraction, sensitivityCurrency, -discountFactor * yearFraction);
  }