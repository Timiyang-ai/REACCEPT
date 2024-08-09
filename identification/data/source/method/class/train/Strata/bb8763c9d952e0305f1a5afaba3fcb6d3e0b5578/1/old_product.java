public default SwaptionScenarioMarketData marketDataView(CalculationMarketData marketData) {
    return DefaultSwaptionScenarioMarketData.of(this, marketData);
  }