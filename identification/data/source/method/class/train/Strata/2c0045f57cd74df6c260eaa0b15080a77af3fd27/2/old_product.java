@Override
  public FxIndexSensitivity multipliedBy(double factor) {
    return new FxIndexSensitivity(index, currency, baseCurrency, fixingDate, sensitivity * factor);
  }