@Override
  public IborFutureOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new IborFutureOptionSensitivity(
        index, expiration, fixingDate, strikePrice, futurePrice, currency, sensitivity);
  }