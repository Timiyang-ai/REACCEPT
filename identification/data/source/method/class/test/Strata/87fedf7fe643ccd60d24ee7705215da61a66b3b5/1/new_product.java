@Override
  public InflationRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new InflationRateSensitivity(observation, currency, sensitivity);
  }