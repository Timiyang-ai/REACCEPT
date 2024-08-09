static CurrencyValuesArray presentValue(
      ResolvedCdsTrade trade,
      ScenarioMarketData marketData) {

    return CurrencyValuesArray.of(
        marketData.getScenarioCount(),
        i -> calculatePresentValue(trade, marketData.scenario(i)));
  }