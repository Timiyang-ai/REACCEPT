@Override
  public FxOptionSensitivity multipliedBy(double factor) {
    return new FxOptionSensitivity(
        currencyPair, expiry, strike, forward, currency, sensitivity * factor);
  }