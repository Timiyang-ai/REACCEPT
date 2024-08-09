@Override
  public IssuerCurveZeroRateSensitivity multipliedBy(double factor) {
    return new IssuerCurveZeroRateSensitivity(curveCurrency, yearFraction, currency, legalEntityGroup, sensitivity * factor);
  }