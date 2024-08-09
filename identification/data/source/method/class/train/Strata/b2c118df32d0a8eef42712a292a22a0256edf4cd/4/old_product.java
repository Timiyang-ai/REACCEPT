@Override
  public FxOptionSensitivity multipliedBy(double factor) {
    return new FxOptionSensitivity(
        currencyPair, expiryDateTime, strike, forward, currency, sensitivity * factor);
  }