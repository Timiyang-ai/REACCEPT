@Override
  public SwaptionSabrSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new SwaptionSabrSensitivity(convention, expiry, tenor, sensitivityType, currency, sensitivity);
  }