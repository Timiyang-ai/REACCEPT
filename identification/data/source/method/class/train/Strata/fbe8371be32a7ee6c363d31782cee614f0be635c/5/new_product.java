@Override
  public SwaptionSabrSensitivity multipliedBy(double factor) {
    return new SwaptionSabrSensitivity(volatilitiesName, expiry, tenor, sensitivityType, currency, sensitivity * factor);
  }