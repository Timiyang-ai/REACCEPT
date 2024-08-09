@Override
  public FxIndexSensitivity multipliedBy(double factor) {
    return new FxIndexSensitivity(index, referenceCurrency, fixingDate, currency, sensitivity * factor);
  }