@Override
  public FxForwardSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new FxForwardSensitivity(currencyPair, currency, referenceCurrency, referenceDate, sensitivity);
  }