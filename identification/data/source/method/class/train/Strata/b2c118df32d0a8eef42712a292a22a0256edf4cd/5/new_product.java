@Override
  public FxOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new FxOptionSensitivity(
        currencyPair, expiryDate, strike, forward, currency, sensitivity);
  }