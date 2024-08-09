public default RatesScenarioMarketData marketDataView(ScenarioMarketData marketData) {
    return DefaultRatesScenarioMarketData.of(this, marketData);
  }