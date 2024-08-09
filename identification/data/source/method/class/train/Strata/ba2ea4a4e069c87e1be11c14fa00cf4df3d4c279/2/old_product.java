@Override
  public BondFutureOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new BondFutureOptionSensitivity(
        futureSecurityId, expiry, futureExpiryDate, strikePrice, futurePrice, currency, sensitivity);
  }