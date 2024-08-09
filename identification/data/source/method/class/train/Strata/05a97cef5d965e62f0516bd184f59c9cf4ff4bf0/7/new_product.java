@Override
  public ZeroRateSensitivity multipliedBy(double factor) {
    return new ZeroRateSensitivity(curveCurrency, yearFraction, currency, sensitivity * factor);
  }