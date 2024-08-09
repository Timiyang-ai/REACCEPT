static SingleScenarioResult<LegAmounts> legInitialNotional(
      SwapTrade trade,
      ExpandedSwap product,
      CalculationMarketData marketData) {

    LegAmounts legInitialNotional = calculateLegInitialNotional(trade);
    return SingleScenarioResult.of(marketData.getScenarioCount(), legInitialNotional);
  }