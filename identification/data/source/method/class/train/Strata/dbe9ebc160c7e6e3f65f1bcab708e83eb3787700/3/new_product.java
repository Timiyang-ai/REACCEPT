@Override
  public RepoCurveZeroRateSensitivity multipliedBy(double factor) {
    return new RepoCurveZeroRateSensitivity(curveCurrency, yearFraction, currency, repoGroup, sensitivity * factor);
  }