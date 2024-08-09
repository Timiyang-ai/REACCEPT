@Override
  public IborFutureOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new IborFutureOptionSensitivity(
        index, expiry, fixingDate, strikePrice, futurePrice, currency, sensitivity);
  }