@Override
  public SwaptionSensitivity multipliedBy(double factor) {
    return new SwaptionSensitivity(index, expiry, tenor, strike, forward, currency, sensitivity * factor);
  }