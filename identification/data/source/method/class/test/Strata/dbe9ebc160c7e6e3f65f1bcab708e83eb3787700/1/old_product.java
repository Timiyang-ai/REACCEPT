@Override
  public RepoCurveZeroRateSensitivity multipliedBy(double factor) {
    return new RepoCurveZeroRateSensitivity(curveCurrency, date, currency, bondGroup, sensitivity * factor);
  }