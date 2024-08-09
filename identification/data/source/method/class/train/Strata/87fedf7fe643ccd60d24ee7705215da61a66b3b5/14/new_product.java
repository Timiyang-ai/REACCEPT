@Override
  public OvernightRateSensitivity multipliedBy(double factor) {
    return new OvernightRateSensitivity(observation, endDate, currency, sensitivity * factor);
  }