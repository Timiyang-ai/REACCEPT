@Override
  public InflationRateSensitivity multipliedBy(double factor) {
    return new InflationRateSensitivity(observation, currency, sensitivity * factor);
  }