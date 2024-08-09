@Override
  public BondFutureOptionSensitivity multipliedBy(double factor) {
    return new BondFutureOptionSensitivity(
        volatilitiesName, expiry, futureExpiryDate, strikePrice, futurePrice, currency, sensitivity * factor);
  }