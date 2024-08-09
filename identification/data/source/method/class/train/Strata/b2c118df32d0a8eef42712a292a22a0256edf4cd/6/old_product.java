@Override
  public FxOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new FxOptionSensitivity(currencyPair, expiry, strike, forward, currency, sensitivity);
  }