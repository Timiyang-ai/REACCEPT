@Override
  public CreditCurveZeroRateSensitivity convertedTo(Currency resultCurrency, FxRateProvider rateProvider) {
    return (CreditCurveZeroRateSensitivity) PointSensitivity.super.convertedTo(resultCurrency, rateProvider);
  }