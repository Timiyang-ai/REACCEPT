public CreditCurveZeroRateSensitivity zeroRatePointSensitivity(LocalDate date, Currency sensitivityCurrency) {
    ZeroRateSensitivity zeroRateSensitivity = survivalProbabilities.zeroRatePointSensitivity(date, sensitivityCurrency);
    return CreditCurveZeroRateSensitivity.of(zeroRateSensitivity, legalEntityId);
  }