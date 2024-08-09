@Override
  public double fxRate(Currency baseCurrency, Currency counterCurrency) {
    if (baseCurrency.equals(counterCurrency)) {
      return 1d;
    }
    return fxMatrix.fxRate(baseCurrency, counterCurrency);
  }