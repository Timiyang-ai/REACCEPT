@Override
  public FxForwardSensitivity multipliedBy(double factor) {
    return new FxForwardSensitivity(currencyPair, referenceCurrency, referenceDate, currency, sensitivity * factor);
  }