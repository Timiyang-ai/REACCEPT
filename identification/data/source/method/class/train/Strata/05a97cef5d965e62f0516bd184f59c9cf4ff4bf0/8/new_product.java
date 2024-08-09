@Override
  public ZeroRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new ZeroRateSensitivity(curveCurrency, yearFraction, currency, sensitivity);
  }