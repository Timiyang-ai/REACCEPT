static CurrencyScenarioArray presentValue(
      ResolvedCdsTrade trade,
      ScenarioMarketData marketData) {

    return CurrencyScenarioArray.of(
        marketData.getScenarioCount(),
        i -> calculatePresentValue(trade, marketData.scenario(i)));
  }