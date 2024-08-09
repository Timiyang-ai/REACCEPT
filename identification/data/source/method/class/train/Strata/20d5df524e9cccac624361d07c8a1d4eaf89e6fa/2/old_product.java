public RepoCurveZeroRateSensitivity zeroRatePointSensitivity(LocalDate date, Currency sensitivityCurrency) {
    ZeroRateSensitivity zeroRateSensitivity = discountFactors.zeroRatePointSensitivity(date, sensitivityCurrency);
    return RepoCurveZeroRateSensitivity.of(zeroRateSensitivity, bondGroup);
  }