@Override
  public default double fxRate(CurrencyPair currencyPair) {
    return fxRate(currencyPair.getBase(), currencyPair.getCounter());
  }