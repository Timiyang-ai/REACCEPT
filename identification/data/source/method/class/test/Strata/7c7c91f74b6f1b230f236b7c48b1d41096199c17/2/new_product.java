@Override
  public CreditCurveZeroRateSensitivity withCurrency(Currency currency) {
    if (this.zeroRateSensitivity.getCurrency().equals(currency)) {
      return this;
    }
    return new CreditCurveZeroRateSensitivity(legalEntityId, zeroRateSensitivity.withCurrency(currency));
  }