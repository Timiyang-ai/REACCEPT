@Override
  public CreditCurveZeroRateSensitivity multipliedBy(double factor) {
    return new CreditCurveZeroRateSensitivity(legalEntityId, zeroRateSensitivity.multipliedBy(factor));
  }