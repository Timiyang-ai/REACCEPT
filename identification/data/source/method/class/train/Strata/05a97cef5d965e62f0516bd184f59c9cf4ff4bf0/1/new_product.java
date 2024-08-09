@Override
  public ZeroRateSensitivity multipliedBy(double factor) {
    return new ZeroRateSensitivity(currencyCurve, date, currency, sensitivity * factor);
  }