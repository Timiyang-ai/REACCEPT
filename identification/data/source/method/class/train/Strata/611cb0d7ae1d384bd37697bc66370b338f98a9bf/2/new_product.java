@Override
  public SwaptionSensitivity multipliedBy(double factor) {
    return new SwaptionSensitivity(volatilitiesName, expiry, tenor, strike, forward, currency, sensitivity * factor);
  }