@Override
  public ZeroRateSensitivity multipliedBy(double factor) {
    return new ZeroRateSensitivity(curveCurrency, currency, date, sensitivity * factor);
  }