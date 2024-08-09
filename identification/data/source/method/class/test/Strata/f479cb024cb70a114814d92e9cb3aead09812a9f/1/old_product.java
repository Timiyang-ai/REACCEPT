public CreditCurveZeroRateSensitivity zeroRatePointSensitivity(double yearFraction, Currency sensitivityCurrency) {
    ZeroRateSensitivity zeroRateSensitivity = survivalProbabilities.zeroRatePointSensitivity(yearFraction, sensitivityCurrency);
    return CreditCurveZeroRateSensitivity.of(zeroRateSensitivity, legalEntityId);
  }