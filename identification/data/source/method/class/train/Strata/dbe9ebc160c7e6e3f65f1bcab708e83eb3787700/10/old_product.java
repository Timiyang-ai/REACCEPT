@Override
  public IssuerCurveZeroRateSensitivity multipliedBy(double factor) {
    return new IssuerCurveZeroRateSensitivity(curveCurrency, date, currency, legalEntityGroup, sensitivity * factor);
  }