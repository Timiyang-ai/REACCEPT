static SingleScenarioResult<LegAmounts> legInitialNotional(
      ResolvedSwapTrade trade,
      RatesScenarioMarketData marketData) {

    LegAmounts legInitialNotional = calculateLegInitialNotional(trade);
    return SingleScenarioResult.of(marketData.getScenarioCount(), legInitialNotional);
  }