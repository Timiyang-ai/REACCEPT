ScenarioArray<LegAmounts> legInitialNotional(
      ResolvedSwapTrade trade,
      RatesScenarioMarketData marketData) {

    LegAmounts legInitialNotional = legInitialNotional(trade);
    return ScenarioArray.ofSingleValue(marketData.getScenarioCount(), legInitialNotional);
  }