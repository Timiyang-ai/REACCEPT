@Override
  public InflationRateSensitivity multipliedBy(double factor) {
    return new InflationRateSensitivity(index, referenceMonth, currency, sensitivity * factor);
  }