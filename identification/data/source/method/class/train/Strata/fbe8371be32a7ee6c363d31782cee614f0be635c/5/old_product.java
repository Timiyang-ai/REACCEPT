@Override
  public SwaptionSabrSensitivity multipliedBy(double factor) {
    return new SwaptionSabrSensitivity(convention, expiry, tenor, sensitivityType, currency, sensitivity * factor);
  }