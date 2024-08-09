public CalculationResult execute(ScenarioMarketData marketData) {
    DefaultCalculationMarketData calculationData = new DefaultCalculationMarketData(marketData, marketDataMappings);
    Result<?> result = Result.of(() -> function.execute(target, calculationData, reportingRules));
    return CalculationResult.of(target, rowIndex, columnIndex, result);
  }