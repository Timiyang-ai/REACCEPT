@Override
  public ZeroRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new ZeroRateSensitivity(curveCurrency, currency, date, sensitivity);
  }