@Override
  public ZeroRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new ZeroRateSensitivity(currencyCurve, date, currency, sensitivity);
  }