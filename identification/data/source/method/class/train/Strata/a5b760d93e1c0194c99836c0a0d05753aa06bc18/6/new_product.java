static ScenarioArray<LegAmounts> legInitialNotional(
      ResolvedSwapTrade trade,
      RatesScenarioMarketData marketData) {

    LegAmounts legInitialNotional = calculateLegInitialNotional(trade);
    return ScenarioArray.ofSingleValue(marketData.getScenarioCount(), legInitialNotional);
  }