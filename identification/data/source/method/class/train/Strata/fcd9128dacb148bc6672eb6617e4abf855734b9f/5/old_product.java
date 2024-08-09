@Override
  public IborFutureOptionSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new IborFutureOptionSensitivity(
        index, expiryDate, fixingDate, strikePrice, futurePrice, currency, sensitivity);
  }