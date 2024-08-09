@Override
  public SwaptionSensitivity multipliedBy(double factor) {
    return new SwaptionSensitivity(convention, expiry, tenor, strike, forward, currency, sensitivity * factor);
  }