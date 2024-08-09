@Override
  public FxForwardSensitivity multipliedBy(double factor) {
    return new FxForwardSensitivity(currencyPair, currency, referenceCurrency, referenceDate, sensitivity * factor);
  }