@Override
  public IssuerCurveZeroRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new IssuerCurveZeroRateSensitivity(curveCurrency, yearFraction, currency, legalEntityGroup, sensitivity);
  }