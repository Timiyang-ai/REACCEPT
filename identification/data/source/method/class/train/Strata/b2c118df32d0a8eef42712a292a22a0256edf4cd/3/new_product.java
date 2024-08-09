@Override
  public FxOptionSensitivity multipliedBy(double factor) {
    return new FxOptionSensitivity(
        currencyPair, expiryDate, strike, forward, currency, sensitivity * factor);
  }