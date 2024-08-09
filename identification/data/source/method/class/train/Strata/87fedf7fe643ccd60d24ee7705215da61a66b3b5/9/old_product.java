@Override
  public OvernightRateSensitivity multipliedBy(double factor) {
    return new OvernightRateSensitivity(index, currency, fixingDate, endDate, sensitivity * factor);
  }