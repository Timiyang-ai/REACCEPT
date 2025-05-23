@Override
  public FxIndexSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new FxIndexSensitivity(index, currency, baseCurrency, fixingDate, sensitivity);
  }