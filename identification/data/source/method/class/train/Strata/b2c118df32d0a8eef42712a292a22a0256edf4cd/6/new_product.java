@Override
  public FxOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new FxOptionSensitivity(volatilitiesName, currencyPair, expiry, strike, forward, currency, sensitivity);
  }