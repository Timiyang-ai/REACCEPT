public CurrencyParameterSensitivities parameterSensitivity(CreditCurveZeroRateSensitivity pointSensitivity) {
    return survivalProbabilities.parameterSensitivity(pointSensitivity.toZeroRateSensitivity());
  }