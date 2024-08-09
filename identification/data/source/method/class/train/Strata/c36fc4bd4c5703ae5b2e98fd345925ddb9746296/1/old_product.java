public default double fxRate(Currency baseCurrency, Currency counterCurrency, int scenarioIndex) {
    return fxRateProvider(scenarioIndex).fxRate(baseCurrency, counterCurrency);
  }