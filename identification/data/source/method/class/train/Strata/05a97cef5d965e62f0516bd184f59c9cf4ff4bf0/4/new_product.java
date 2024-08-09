@Override
  public ZeroRateSensitivity multipliedBy(double factor) {
    return new ZeroRateSensitivity(curveCurrency, date, currency, sensitivity * factor);
  }