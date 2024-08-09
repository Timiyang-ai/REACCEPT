@Override
  public double fxRate(Currency baseCurrency, Currency counterCurrency) {
    return fxRateProvider.fxRate(baseCurrency, counterCurrency);
  }