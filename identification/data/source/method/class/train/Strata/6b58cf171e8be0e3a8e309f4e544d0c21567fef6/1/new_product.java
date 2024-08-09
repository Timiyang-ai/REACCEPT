@Override
  public SwaptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new SwaptionSensitivity(volatilitiesName, expiry, tenor, strike, forward, currency, sensitivity);
  }