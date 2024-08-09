@Override
  public InflationRateSensitivity multipliedBy(double factor) {
    return new InflationRateSensitivity(index, currency, referenceMonth, sensitivity * factor);
  }