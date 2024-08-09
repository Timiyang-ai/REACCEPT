@Override
  public CreditCurveZeroRateSensitivity multipliedBy(double factor) {
    return new CreditCurveZeroRateSensitivity(curveCurrency, legalEntityId, yearFraction, currency, sensitivity * factor);
  }