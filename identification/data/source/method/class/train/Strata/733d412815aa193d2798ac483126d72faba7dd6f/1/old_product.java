@Override
  public ZeroRateSensitivity zeroRatePointSensitivity(LocalDate date, Currency sensitivityCurrency) {
    double relativeTime = relativeTime(date);
    double discountFactor = discountFactor(relativeTime);
    return ZeroRateSensitivity.of(currency, sensitivityCurrency, date, -discountFactor * relativeTime);
  }