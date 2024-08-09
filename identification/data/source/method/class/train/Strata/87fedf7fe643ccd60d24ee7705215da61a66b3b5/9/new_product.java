@Override
  public OvernightRateSensitivity multipliedBy(double factor) {
    return new OvernightRateSensitivity(index, fixingDate, endDate, currency, sensitivity * factor);
  }