@Override
  public FxIndexSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new FxIndexSensitivity(index, referenceCurrency, fixingDate, currency, sensitivity);
  }