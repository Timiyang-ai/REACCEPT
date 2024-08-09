public default RatesScenarioMarketData marketDataView(CalculationMarketData marketData) {
    return DefaultRatesScenarioMarketData.of(this, marketData);
  }