@Override
  public IborRateSensitivity withCurrency(Currency currency) {
    if (this.currency.equals(currency)) {
      return this;
    }
    return new IborRateSensitivity(index, fixingDate, currency, sensitivity);
  }