@Override
  public IborCapletFloorletSensitivity multipliedBy(double factor) {
    return new IborCapletFloorletSensitivity(index, expiry, strike, forward, currency, sensitivity * factor);
  }