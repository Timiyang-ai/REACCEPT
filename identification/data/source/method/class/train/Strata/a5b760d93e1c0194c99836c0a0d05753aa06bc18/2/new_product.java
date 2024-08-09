static SingleScenarioResult<LegAmounts> legInitialNotional(
      ResolvedSwapTrade trade,
      CalculationMarketData marketData) {

    LegAmounts legInitialNotional = calculateLegInitialNotional(trade);
    return SingleScenarioResult.of(marketData.getScenarioCount(), legInitialNotional);
  }