public default SwaptionScenarioMarketData marketDataView(ScenarioMarketData marketData) {
    return DefaultSwaptionScenarioMarketData.of(this, marketData);
  }