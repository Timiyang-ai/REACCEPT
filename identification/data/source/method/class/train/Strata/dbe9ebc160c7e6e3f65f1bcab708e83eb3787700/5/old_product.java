@Override
  public RepoCurveZeroRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new RepoCurveZeroRateSensitivity(curveCurrency, date, currency, bondGroup, sensitivity);
  }