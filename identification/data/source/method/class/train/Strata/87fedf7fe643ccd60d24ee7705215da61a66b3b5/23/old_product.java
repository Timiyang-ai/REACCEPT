@Override
  public InflationRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new InflationRateSensitivity(index, currency, referenceMonth, sensitivity);
  }