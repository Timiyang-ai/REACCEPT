@Override
  public FxOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new FxOptionSensitivity(currencyPair, expiryDateTime, strike, forward, currency, sensitivity);
  }