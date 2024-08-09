@Override
  public OvernightRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new OvernightRateSensitivity(index, currency, fixingDate, endDate, sensitivity);
  }