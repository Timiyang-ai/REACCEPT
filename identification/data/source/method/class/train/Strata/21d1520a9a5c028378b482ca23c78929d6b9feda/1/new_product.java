@Override
  public IborCapletFloorletSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new IborCapletFloorletSensitivity(volatilitiesName, expiry, strike, forward, currency, sensitivity);
  }