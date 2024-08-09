@Override
  public ZeroRateSensitivity multipliedBy(double factor) {
    return new ZeroRateSensitivity(currency, date, sensitivity * factor);
  }