@Override
  public RepoCurveZeroRateSensitivity multipliedBy(double factor) {
    return new RepoCurveZeroRateSensitivity(curveCurrency, yearFraction, currency, bondGroup, sensitivity * factor);
  }