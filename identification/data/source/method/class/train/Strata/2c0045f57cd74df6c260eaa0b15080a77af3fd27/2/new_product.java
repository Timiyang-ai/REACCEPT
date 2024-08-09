@Override
  public FxIndexSensitivity multipliedBy(double factor) {
    return new FxIndexSensitivity(index, currency, referenceCurrency, fixingDate, sensitivity * factor);
  }