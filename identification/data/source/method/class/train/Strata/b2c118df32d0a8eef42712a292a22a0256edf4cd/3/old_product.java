@Override
  public FxOptionSensitivity multipliedBy(double factor) {
    return new FxOptionSensitivity(
        index, expiryDate, strike, forward, currency, sensitivity * factor);
  }