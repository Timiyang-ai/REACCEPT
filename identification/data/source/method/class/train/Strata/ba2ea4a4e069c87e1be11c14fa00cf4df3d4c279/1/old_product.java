@Override
  public BondFutureOptionSensitivity multipliedBy(double factor) {
    return new BondFutureOptionSensitivity(
        futureSecurityId, expiry, futureExpiryDate, strikePrice, futurePrice, currency, sensitivity * factor);
  }