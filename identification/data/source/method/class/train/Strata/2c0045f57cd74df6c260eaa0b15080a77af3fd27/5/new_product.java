@Override
  public FxIndexSensitivity multipliedBy(double factor) {
    return new FxIndexSensitivity(observation, referenceCurrency, currency, sensitivity * factor);
  }