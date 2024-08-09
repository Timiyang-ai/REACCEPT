@Override
  public IborCapletFloorletSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new IborCapletFloorletSensitivity(index, expiry, strike, forward, currency, sensitivity);
  }