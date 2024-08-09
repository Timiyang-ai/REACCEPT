@Override
  public CreditCurveZeroRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new CreditCurveZeroRateSensitivity(curveCurrency, legalEntityId, yearFraction, currency, sensitivity);
  }