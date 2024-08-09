@Override
  public ZeroRateSensitivity zeroRatePointSensitivity(LocalDate date, Currency sensitivityCurrency) {
    double relativeYearFraction = relativeYearFraction(date);
    double discountFactor = discountFactor(relativeYearFraction);
    return ZeroRateSensitivity.of(currency, sensitivityCurrency, date, -discountFactor * relativeYearFraction);
  }