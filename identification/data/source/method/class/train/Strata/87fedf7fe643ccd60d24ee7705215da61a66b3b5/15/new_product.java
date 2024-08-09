@Override
  public ZeroRateSensitivity zeroRatePointSensitivity(LocalDate date, Currency sensitivityCurrency) {
    double yearFraction = relativeYearFraction(date);
    double discountFactor = discountFactor(yearFraction);
    return ZeroRateSensitivity.of(currency, date, sensitivityCurrency, -discountFactor * yearFraction);
  }