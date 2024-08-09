public default double fxRate(Currency baseCurrency, Currency counterCurrency, int scenarioIndex) {
    if (baseCurrency.equals(counterCurrency)) {
      return 1;
    }
    return fxRateProvider(scenarioIndex).fxRate(baseCurrency, counterCurrency);
  }