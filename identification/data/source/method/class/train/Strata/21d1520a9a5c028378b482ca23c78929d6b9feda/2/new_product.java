@Override
  public IborCapletFloorletSensitivity multipliedBy(double factor) {
    return new IborCapletFloorletSensitivity(volatilitiesName, expiry, strike, forward, currency, sensitivity * factor);
  }